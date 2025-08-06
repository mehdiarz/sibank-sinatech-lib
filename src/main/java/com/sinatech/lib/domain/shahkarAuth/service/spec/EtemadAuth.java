package com.sinatech.lib.domain.shahkarAuth.service.spec;

import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkCode.CheckCodeResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard.IdCardResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms.ShahkarResponse;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoRequest;
import com.sinatech.lib.domain.shahkarAuth.dto.video.VideoResponse;

public interface EtemadAuth {
    ShahkarResponse sendShahkar(ShahkarRequest shahkarRequest);
    CheckCodeResponse verifyOtp(CheckCodeRequest request);
    IdCardResponse verifyIdCard(IdCardRequest request, String token);
    VideoResponse verifyVideo(VideoRequest request, String token);

}

