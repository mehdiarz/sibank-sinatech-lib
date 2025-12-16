package com.sinatech.lib.provider.ApiGateway.auth.util;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.provider.rialDigital.util.RialDigitalRestClient;
import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenRestClient {
    public static Response postRequest(String url, Map<String, String> formData) {  // تغییر به Map<String, String> برای فرم
        Response response;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        try {
            FormBody.Builder formBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = formBuilder.build();

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
        } catch (IOException e) {
            CommonUtil.logError(RialDigitalRestClient.class.getSimpleName(), "postRequest", e.getMessage(), ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(formData))
                    .output(e.toString())
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
        return response;
    }

    public static String responseBodyToString(Response response) {
        String responseString = "";
        try {
            responseString = response.body().string();
        } catch (IOException e) {
            CommonUtil.logError(RialDigitalRestClient.class.getSimpleName(), "responseBodyToString", e.getMessage());
        }
        return responseString;
    }
}