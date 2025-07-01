package com.sinatech.lib.domain.rialDigital.service.spec;

import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenReponse;
import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenRequest;

public interface RialDigital {
    RialDigitalGetTokenReponse getToken(RialDigitalGetTokenRequest findAllRequest);
}
