package com.sinatech.lib.domain.shahkarAuth.service;

import com.rahand.common.dto.ResponseDto;

import com.sinatech.lib.domain.shahkarAuth.dto.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.service.spec.EtemadAuth;
import com.sinatech.lib.provider.shahkar.EtemadAuthService;


public class CheckCodeService {

    private final EtemadAuth checkCode;

    public CheckCodeService() {
        this.checkCode = new EtemadAuthService();
    }

    public ResponseDto<CheckCodeResponse> verifyOtp(CheckCodeRequest request) {
        ResponseDto<CheckCodeResponse> responseDto = new ResponseDto<>();
        CheckCodeResponse response = checkCode.verifyOtp(request);

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
