package com.sinatech.lib.provider.ApiGateway.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.domain.bank.dto.*;
import com.sinatech.lib.domain.bank.service.spec.cheque;
import com.sinatech.lib.provider.ApiGateway.auth.util.TokenRestClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TokenService implements cheque {
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private static final String TOKEN_SERVICE_URL = "http://10.16.224.5:30021/oauth/token";
    private static final String CLIENT_ID = "microservice-ecosystem";
    private static final String CLIENT_SECRET = "5f9af718-f213-4272-a2a4-da17b9af139b";
    private static final String USERNAME = "sinabankuser";
    private static final String PASSWORD = "sinabankpassword";

    private final ObjectMapper objectMapper;
    private TokenResponse currentToken;
    private Date tokenExpiryTime;
    private Date refreshTokenExpiryTime;

    public TokenService() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        try {
            logger.info("Requesting token from: {}", TOKEN_SERVICE_URL);


            String clientId = tokenRequest.getClientId() != null && !tokenRequest.getClientId().isEmpty()
                    ? tokenRequest.getClientId() : CLIENT_ID;
            String clientSecret = tokenRequest.getClientSecret() != null && !tokenRequest.getClientSecret().isEmpty()
                    ? tokenRequest.getClientSecret() : CLIENT_SECRET;
            String username = tokenRequest.getUsername() != null && !tokenRequest.getUsername().isEmpty()
                    ? tokenRequest.getUsername() : USERNAME;
            String password = tokenRequest.getPassword() != null && !tokenRequest.getPassword().isEmpty()
                    ? tokenRequest.getPassword() : PASSWORD;
            String grantType = tokenRequest.getGrantType() != null && !tokenRequest.getGrantType().isEmpty()
                    ? tokenRequest.getGrantType() : "password";
            String refreshToken = tokenRequest.getRefreshToken();


            Map<String, String> formData = new LinkedHashMap<>();
            formData.put("client_id", clientId);
            formData.put("client_secret", clientSecret);
            formData.put("grant_type", grantType);

            if ("refresh_token".equals(grantType) && refreshToken != null) {
                formData.put("refresh_token", refreshToken);
            } else {
                formData.put("username", username);
                formData.put("password", password);
            }


            Response response = TokenRestClient.postRequest(TOKEN_SERVICE_URL, formData);

            if (response.isSuccessful()) {
                String jsonResponse = TokenRestClient.responseBodyToString(response);
                logger.debug("Token response: {}", jsonResponse);

                TokenResponse tokenResponse = objectMapper.readValue(jsonResponse, TokenResponse.class);


                if (tokenResponse.getAccessToken() == null || tokenResponse.getAccessToken().isEmpty()) {
                    tokenResponse.setError(true);
                    tokenResponse.setErrorMessage("Token response does not contain access_token");
                } else {

                    saveToken(tokenResponse);
                    logger.info("Token acquired successfully. Expires in: {} seconds", tokenResponse.getExpiresIn());
                }

                return tokenResponse;
            } else {
                String errorBody = TokenRestClient.responseBodyToString(response);
                logger.error("Token request failed with status: {}, body: {}", response.code(), errorBody);

                CommonUtil.logError(TokenService.class.getSimpleName(), "getToken", errorBody,
                        ServiceLogsDto.builder()
                                .input(CommonUtil.toJson(formData))
                                .output(errorBody)
                                .build());

                TokenResponse errorResponse = new TokenResponse();
                errorResponse.setError(true);
                errorResponse.setErrorMessage("HTTP " + response.code() + ": " + errorBody);
                return errorResponse;
            }
        } catch (Exception e) {
            logger.error("Exception while getting token", e);
            TokenResponse errorResponse = new TokenResponse();
            errorResponse.setError(true);
            errorResponse.setErrorMessage("Exception: " + e.getMessage());
            return errorResponse;
        }
    }

    @Override
    public TokenResponse getCurrentToken(TokenRequest tokenRequest) {

        if (currentToken != null && !isTokenExpired()) {
            return currentToken;
        }


        if (currentToken != null && currentToken.getRefreshToken() != null && !isRefreshTokenExpired()) {
            logger.info("Token expired, refreshing with refresh token");
            TokenRequest refreshRequest = new TokenRequest();
            refreshRequest.setGrantType("refresh_token");
            refreshRequest.setRefreshToken(currentToken.getRefreshToken());
            return getToken(refreshRequest);
        }


        logger.info("No valid token, requesting new token");
        return getToken(tokenRequest);
    }

    @Override
    public ChequeInquiryResponse chequeInquiry(ChequeInquiryRequest chequeInquiryRequest) {
        ChequeInquiryResponse response = new ChequeInquiryResponse();
        response.setError(true);
        response.setErrorMessage("TokenService does not support cheque operations. Use ChequeProviderService instead.");
        return response;
    }

    @Override
    public ChequeAcceptOrRejectResponse chequeAcceptOrReject(ChequeAcceptOrRejectRequest request) {
        ChequeAcceptOrRejectResponse response = new ChequeAcceptOrRejectResponse();
        response.setError(true);
        response.setErrorMessage("TokenService does not support cheque operations. Use ChequeProviderService instead.");
        return response;
    }

    private void saveToken(TokenResponse tokenResponse) {
        this.currentToken = tokenResponse;


        if (tokenResponse.getExpiresIn() != null) {
            long expiresInMillis = (tokenResponse.getExpiresIn() - 60) * 1000L; // 60 ثانیه زودتر
            this.tokenExpiryTime = new Date(System.currentTimeMillis() + expiresInMillis);
        }


        if (tokenResponse.getRefreshExpiresIn() != null) {
            long refreshExpiresInMillis = (tokenResponse.getRefreshExpiresIn() - 60) * 1000L;
            this.refreshTokenExpiryTime = new Date(System.currentTimeMillis() + refreshExpiresInMillis);
        }

        logger.debug("Token saved. Access token expires at: {}, Refresh token expires at: {}",
                tokenExpiryTime, refreshTokenExpiryTime);
    }

    private boolean isTokenExpired() {
        if (tokenExpiryTime == null) {
            return true;
        }
        return new Date().after(tokenExpiryTime);
    }

    private boolean isRefreshTokenExpired() {
        if (refreshTokenExpiryTime == null) {
            return true;
        }
        return new Date().after(refreshTokenExpiryTime);
    }


    public String getCurrentAccessToken() {
        TokenRequest request = new TokenRequest();
        TokenResponse response = getCurrentToken(request);
        if (response.isError()) {
            throw new CustomRuntimeException("Failed to get current token: " + response.getErrorMessage());
        }
        return response.getAccessToken();
    }


    public TokenResponse getCurrentTokenResponse() {
        return currentToken;
    }

    public Date getTokenExpiryTime() {
        return tokenExpiryTime;
    }

    public Date getRefreshTokenExpiryTime() {
        return refreshTokenExpiryTime;
    }
}