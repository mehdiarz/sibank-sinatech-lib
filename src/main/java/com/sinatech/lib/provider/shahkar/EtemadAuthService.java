package com.sinatech.lib.provider.shahkar;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.config.IntegrationLibConfig;
import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoResponse;
import com.sinatech.lib.domain.shahkarAuth.service.spec.EtemadAuth;
import com.sinatech.lib.provider.rialDigital.util.RialDigitalRestClient;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EtemadAuthService implements EtemadAuth {

    private final String BASE_URL = IntegrationLibConfig.getProperty("SHAHKAR_BASE_URL");

    @Override
    public ShahkarResponse sendShahkar(ShahkarRequest request) {
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("nationalCode", request.getNationalCode());
        requestMap.put("phone", request.getPhone());

        String url = BASE_URL + "shahkarSMS/";
        Response response = RialDigitalRestClient.postRequest(url, requestMap);

        if (response.isSuccessful()) {
            String jsonResponse = RialDigitalRestClient.responseBodyToString(response);
            return CommonUtil.jsonToObject(jsonResponse, ShahkarResponse.class);
        } else {
            String jsonResponse = RialDigitalRestClient.responseBodyToString(response);
            CommonUtil.logError(EtemadAuthService.class.getSimpleName(), "sendShahkar", jsonResponse, ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
                    .output(jsonResponse)
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public CheckCodeResponse verifyOtp(CheckCodeRequest request) {
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("UUIDInput", request.getUUIDInput());
        requestMap.put("otpInput", request.getOtpInput());

        String url = BASE_URL + "checkCode/";
        Response response = RialDigitalRestClient.postRequest(url, requestMap);

        String jsonResponse = RialDigitalRestClient.responseBodyToString(response);
        if (response.isSuccessful()) {
            return CommonUtil.jsonToObject(jsonResponse, CheckCodeResponse.class);
        } else {
            CommonUtil.logError(EtemadAuthService.class.getSimpleName(), "verifyOtp", jsonResponse, ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
                    .output(jsonResponse)
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public IdCardResponse verifyIdCard(IdCardRequest request, String token) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("idCardImg", request.getIdCardImg().getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), request.getIdCardImg()))
                .addFormDataPart("behindIdCardImg", request.getBehindIdCardImg().getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), request.getBehindIdCardImg()))
                .addFormDataPart("birthDate", request.getBirthDate())
                .build();

        Request httpRequest = new Request.Builder()
                .url(BASE_URL + "idCard/")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            String jsonResponse = response.body().string();
            if (response.isSuccessful()) {
                return CommonUtil.jsonToObject(jsonResponse, IdCardResponse.class);
            } else {
                CommonUtil.logError(EtemadAuthService.class.getSimpleName(), "validateCard", jsonResponse, ServiceLogsDto.builder()
                        .input("multipart: image + birthDate")
                        .output(jsonResponse)
                        .build());
                throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
            }
        } catch (IOException e) {
            throw new CustomRuntimeException( e.getMessage());
        }
    }

    @Override
    public VideoResponse verifyVideo(VideoRequest request, String token) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("personVideo", request.getPersonVideo().getName(),
                        RequestBody.create(MediaType.parse("video/mp4"), request.getPersonVideo()))
                .build();

        Request httpRequest = new Request.Builder()
                .url(BASE_URL + "/video/")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            String jsonResponse = response.body().string();
            if (response.isSuccessful()) {
                return CommonUtil.jsonToObject(jsonResponse, VideoResponse.class);
            } else {
                CommonUtil.logError(EtemadAuthService.class.getSimpleName(), "verifyLiveness", jsonResponse, ServiceLogsDto.builder()
                        .input("multipart: video")
                        .output(jsonResponse)
                        .build());
                throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
            }
        } catch (IOException e) {
            throw new CustomRuntimeException("IOException in verifyVideo" + e.getMessage());
        }
    }
}