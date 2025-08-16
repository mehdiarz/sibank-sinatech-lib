package com.sinatech.lib.domain.shahkarAuth.service;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.shahkarAuth.dto.*;
import com.sinatech.lib.domain.shahkarAuth.dto.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.VideoRequest;
import com.sinatech.lib.domain.shahkarAuth.service.spec.EtemadAuth;
import com.sinatech.lib.provider.shahkar.EtemadAuthService;
public class EtemadServices {

    private final EtemadAuth etemadAuth;

    public EtemadServices() {
        this.etemadAuth = new EtemadAuthService();
    }

    public ResponseDto<SimpleResponse> sendShahkar(ShahkarRequest request) {
        return etemadAuth.sendShahkar(request);
    }

    public ResponseDto<SimpleResponse> verifyOtp(CheckCodeRequest request) {
        return etemadAuth.verifyOtp(request);
    }

    public ResponseDto<SimpleResponse> verifyIdCard(IdCardRequest request, String token) {
        return etemadAuth.verifyIdCard(request, token);
    }

    public ResponseDto<SimpleResponse> verifyVideo(VideoRequest request, String token) {
        return etemadAuth.verifyVideo(request, token);
    }
}
