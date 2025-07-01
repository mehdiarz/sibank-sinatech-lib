package com.sinatech.lib.domain.rialDigital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseResponse;
import lombok.Builder;

public class RialDigitalGetTokenReponse extends BaseResponse {
    @JsonProperty("token")
    private String token;

    public RialDigitalGetTokenReponse() {

    }

    @Builder
    public RialDigitalGetTokenReponse(String userId){
        this.token = token;
    }

    public String getUserId() {
        return token;
    }

    public void setUserId(String userId) {
        this.token = token;
    }
}
