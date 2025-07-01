package com.sinatech.lib.domain.rialDigital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseResponse;
import lombok.Builder;

public class RialDigitalGetTokenReponse extends BaseResponse {

    private String token;

    public RialDigitalGetTokenReponse() {

    }

    @Builder
    public RialDigitalGetTokenReponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
