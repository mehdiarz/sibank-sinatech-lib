package com.sinatech.lib.provider.shahkar;

import com.rahand.common.dto.ResponseDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.config.IntegrationLibConfig;
import com.sinatech.lib.domain.shahkarAuth.dto.SimpleResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.VideoRequest;
import com.sinatech.lib.domain.shahkarAuth.service.spec.EtemadAuth;
import com.sinatech.lib.provider.rialDigital.util.RialDigitalRestClient;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class EtemadAuthService implements EtemadAuth {

    private final String BASE_URL = IntegrationLibConfig.getProperty("SHAHKAR_BASE_URL");

    private ResponseDto<SimpleResponse> wrapResponse(Response response, String serviceName) {
        ResponseDto<SimpleResponse> responseDto = new ResponseDto<>();
        try {
            String jsonResponse = RialDigitalRestClient.responseBodyToString(response);

            if (response.isSuccessful()) {
                SimpleResponse simple = CommonUtil.jsonToObject(jsonResponse, SimpleResponse.class);
                responseDto.setError(!simple.isStatus());
                responseDto.setMessage(simple.getMessage());
                responseDto.setResponseData(simple);
            } else {
                responseDto.setError(true);
                responseDto.setMessage("خطا در سرویس " + serviceName);
            }
        } catch (Exception e) {
            throw new CustomRuntimeException("خطا در پردازش پاسخ سرویس " + serviceName + ": " + e.getMessage());
        }
        return responseDto;
    }

    @Override
    public ResponseDto<SimpleResponse> sendShahkar(ShahkarRequest request) {
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("nationalCode", request.getNationalCode());
        requestMap.put("phone", request.getPhone());

        String url = BASE_URL + "shahkarSMS/";
        Response response = RialDigitalRestClient.postRequest(url, requestMap);
        return wrapResponse(response, "شاهکار");
    }

    @Override
    public ResponseDto<SimpleResponse> verifyOtp(CheckCodeRequest request) {
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("UUIDInput", request.getUUIDInput());
        requestMap.put("otpInput", request.getOtpInput());

        String url = BASE_URL + "checkCode/";
        Response response = RialDigitalRestClient.postRequest(url, requestMap);
        return wrapResponse(response, "OTP");
    }

    @Override
    public ResponseDto<SimpleResponse> verifyIdCard(IdCardRequest request, String token) {
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
            return wrapResponse(response, "کارت ملی");
        } catch (IOException e) {
            throw new CustomRuntimeException("IOException in verifyIdCard: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<SimpleResponse> verifyVideo(VideoRequest request, String token) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("personVideo", request.getPersonVideo().getName(),
                        RequestBody.create(MediaType.parse("video/mp4"), request.getPersonVideo()))
                .build();

        Request httpRequest = new Request.Builder()
                .url(BASE_URL + "video/")
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            return wrapResponse(response, "ویدیو");
        } catch (IOException e) {
            throw new CustomRuntimeException("IOException in verifyVideo: " + e.getMessage());
        }
    }
}
