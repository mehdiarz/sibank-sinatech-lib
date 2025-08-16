package com.sinatech.lib.domain.shahkarAuth.service.spec;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.shahkarAuth.dto.SimpleResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.VideoRequest;

public interface EtemadAuth {
    ResponseDto<SimpleResponse> sendShahkar(ShahkarRequest shahkarRequest);
    ResponseDto<SimpleResponse> verifyOtp(CheckCodeRequest request);
    ResponseDto<SimpleResponse> verifyIdCard(IdCardRequest request, String token);
    ResponseDto<SimpleResponse> verifyVideo(VideoRequest request, String token);
}