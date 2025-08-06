package com.sinatech.lib.domain.shahkarAuth.service;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.shahkarAuth.dto.*;
import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoResponse;
import com.sinatech.lib.domain.shahkarAuth.service.spec.EtemadAuth;
import com.sinatech.lib.provider.shahkar.EtemadAuthService;

public class EtemadServices {

    private final EtemadAuth etemadAuth;

    public EtemadServices() {
        this.etemadAuth = new EtemadAuthService();
    }

    public ResponseDto<ShahkarResponse> sendShahkar(ShahkarRequest request) {
        ResponseDto<ShahkarResponse> responseDto = new ResponseDto<>();
        ShahkarResponse response = etemadAuth.sendShahkar(request);

        if (response.isSessionExpired()) {
            response = etemadAuth.sendShahkar(request);
        }

        if (response.isError()) {
            responseDto.setError(true);
            responseDto.setMessage(response.getErrorMessage());
        } else {
            responseDto.setError(false);
        }

        responseDto.setResponseData(response);
        return responseDto;
    }

    public ResponseDto<CheckCodeResponse> verifyOtp(CheckCodeRequest request) {
        ResponseDto<CheckCodeResponse> responseDto = new ResponseDto<>();
        CheckCodeResponse response = etemadAuth.verifyOtp(request);

        if (response.isError()) {
            responseDto.setError(true);
            responseDto.setMessage(response.getErrorMessage());
        } else {
            responseDto.setError(false);
        }

        responseDto.setResponseData(response);
        return responseDto;
    }

    public ResponseDto<IdCardResponse> verifyIdCard(IdCardRequest request, String token) {
        ResponseDto<IdCardResponse> responseDto = new ResponseDto<>();
        IdCardResponse response = etemadAuth.verifyIdCard(request, token);

        if (response.isError()) {
            responseDto.setError(true);
            responseDto.setMessage(response.getErrorMessage());
        } else {
            responseDto.setError(false);
        }

        responseDto.setResponseData(response);
        return responseDto;
    }

    public ResponseDto<VideoResponse> verifyVideo(VideoRequest request, String token) {
        ResponseDto<VideoResponse> responseDto = new ResponseDto<>();
        VideoResponse response = etemadAuth.verifyVideo(request, token);

        if (response.isError()) {
            responseDto.setError(true);
            responseDto.setMessage(response.getErrorMessage());
        } else {
            responseDto.setError(false);
        }

        responseDto.setResponseData(response);
        return responseDto;
    }
}
