package com.sinatech.lib.domain.shahkarAuth.service.spec;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.shahkarAuth.dto.*;

public interface EtemadAuth {
    ResponseDto<SimpleResponse> sendShahkar(ShahkarRequest shahkarRequest);
    ResponseDto<SimpleResponse> verifyOtp(CheckCodeRequest request);

    ResponseDto<SimpleResponse> verifyIdCardWithFile(IdCardRequestWithFile request, String token);

    ResponseDto<SimpleResponse> verifyIdCardWithFile(VideoRequestWithFile request, String token);

    ResponseDto<SimpleResponse> verifyIdCard(IdCardRequest request, String token);
    ResponseDto<SimpleResponse> verifyVideo(VideoRequest request, String token);
}