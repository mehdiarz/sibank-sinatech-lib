package com.sinatech.lib.provider.ApiGateway.cheque.util;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.domain.bank.dto.TokenRequest;
import com.sinatech.lib.domain.bank.dto.TokenResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ChequeRestClient {

    private static com.sinatech.lib.provider.ApiGateway.auth.service.TokenService tokenService;

    public static void setTokenService(com.sinatech.lib.provider.ApiGateway.auth.service.TokenService tokenService) {
        ChequeRestClient.tokenService = tokenService;
    }


    public static Response postRequest(String url, Map<String, Object> requestMap) {
        return postRequestInternal(url, requestMap, 0);
    }

    private static Response postRequestInternal(String url, Map<String, Object> requestMap, int retryCount) {
        if (retryCount > 1) {
            throw new CustomRuntimeException("Maximum retry attempts reached");
        }

        Response response;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        try {
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    CommonUtil.toJson(requestMap)
            );

            Request.Builder requestBuilder = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("locale", "fa_IR")
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody);


            if (tokenService != null) {
                try {
                    TokenRequest tokenRequest = new TokenRequest();
                    TokenResponse tokenResponse = tokenService.getCurrentToken(tokenRequest);

                    if (tokenResponse != null && !tokenResponse.isError() &&
                            tokenResponse.getAccessToken() != null && !tokenResponse.getAccessToken().isEmpty()) {

                        String bearerToken = "Bearer " + tokenResponse.getAccessToken();
                        requestBuilder.addHeader("Authorization", bearerToken);
                    } else {
                        String errorMsg = "Failed to get valid token";
                        if (tokenResponse != null && tokenResponse.getErrorMessage() != null) {
                            errorMsg += ": " + tokenResponse.getErrorMessage();
                        }
                        throw new CustomRuntimeException(errorMsg);
                    }
                } catch (Exception e) {
                    throw new CustomRuntimeException("Failed to get token: " + e.getMessage());
                }
            } else {
                throw new CustomRuntimeException("TokenService is not set. Call ChequeRestClient.setTokenService() first.");
            }

            Request request = requestBuilder.build();
            response = client.newCall(request).execute();


            if (response.code() == 401 && retryCount == 0) {
                response.close();

                if (tokenService != null) {
                    try {
                        TokenRequest tokenRequest = new TokenRequest();
                        tokenService.getToken(tokenRequest);
                        return postRequestInternal(url, requestMap, retryCount + 1);
                    } catch (Exception e) {
                        throw new CustomRuntimeException("Token refresh failed: " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "postRequest", e.getMessage(),
                    ServiceLogsDto.builder()
                            .input(CommonUtil.toJson(requestMap))
                            .output(e.toString())
                            .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
        return response;
    }


    public static Response postRequestWithHeaders(String url, Map<String, Object> requestMap, Map<String, String> headers) {
        Response response;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        try {
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    CommonUtil.toJson(requestMap)
            );

            Request.Builder requestBuilder = new Request.Builder()
                    .url(url)
                    .addHeader("accept", "application/json")
                    .addHeader("locale", "fa_IR")
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody);


            if (tokenService != null) {
                try {
                    TokenRequest tokenRequest = new TokenRequest();
                    TokenResponse tokenResponse = tokenService.getCurrentToken(tokenRequest);

                    if (tokenResponse != null && !tokenResponse.isError() &&
                            tokenResponse.getAccessToken() != null && !tokenResponse.getAccessToken().isEmpty()) {

                        String bearerToken = "Bearer " + tokenResponse.getAccessToken();
                        requestBuilder.addHeader("Authorization", bearerToken);
                    }
                } catch (Exception e) {

                    CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "postRequestWithHeaders",
                            "Failed to get token: " + e.getMessage());
                }
            }


            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    requestBuilder.addHeader(header.getKey(), header.getValue());
                }
            }

            Request request = requestBuilder.build();
            response = client.newCall(request).execute();

        } catch (IOException e) {
            CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "postRequestWithHeaders", e.getMessage(),
                    ServiceLogsDto.builder()
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
            if (response.body() != null) {
                responseString = response.body().string();
            }
        } catch (IOException e) {
            CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "responseBodyToString", e.getMessage());
        }
        return responseString;
    }
}