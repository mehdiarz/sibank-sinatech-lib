package com.sinatech.lib.domain.rialDigital.service;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenReponse;
import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenRequest;
import com.sinatech.lib.domain.rialDigital.service.spec.RialDigital;
import com.sinatech.lib.provider.rialDigital.service.RialDigitalGetTokenService;

public class RialDigitalService {
    private RialDigital rialDigitalService;

    public RialDigitalService(){
        rialDigitalService = new RialDigitalGetTokenService();
    }

    public ResponseDto<RialDigitalGetTokenReponse> getToken(RialDigitalGetTokenRequest rialDigitalGetTokenRequest) {
        ResponseDto<RialDigitalGetTokenReponse> response = new ResponseDto<>();

        RialDigitalGetTokenReponse rialDigitalGetTokenReponse = rialDigitalService.getToken(rialDigitalGetTokenRequest);

        if (rialDigitalGetTokenReponse.isSessionExpired()) {
            rialDigitalGetTokenReponse = rialDigitalService.getToken(rialDigitalGetTokenRequest);
        }

        if (rialDigitalGetTokenReponse.isError()) {
            response.setError(true);
            response.setMessage(rialDigitalGetTokenReponse.getErrorMessage());
            response.setResponseData(rialDigitalGetTokenReponse);

        } else {
            response.setError(false);
            response.setResponseData(rialDigitalGetTokenReponse);
        }
        return response;

    }
}
