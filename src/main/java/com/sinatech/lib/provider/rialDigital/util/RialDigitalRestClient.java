package com.sinatech.lib.provider.rialDigital.util;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RialDigitalRestClient {

    public static Response postRequest(String url, Map requestMap) {

        Response response;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                    , CommonUtil.toJson(requestMap));

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "*/*")
                    .addHeader("Content-Type", "application/json") // Ensure this matches curl
                    .post(requestBody)
                    .build();

            response = client.newCall(request).execute();

        } catch (IOException e) {
            CommonUtil.logError(RialDigitalRestClient.class.getSimpleName(), "postRequest", e.getMessage(), ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
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
