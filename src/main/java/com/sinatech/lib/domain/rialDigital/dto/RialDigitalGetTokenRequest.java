package com.sinatech.lib.domain.rialDigital.dto;

import com.rahand.common.dto.BaseRequest;
import lombok.Builder;

public class RialDigitalGetTokenRequest extends BaseRequest {

    private String userId;


    public RialDigitalGetTokenRequest() {

    }

    @Builder
    public RialDigitalGetTokenRequest(String userId, String baseUrl){
        this.userId = userId;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




}


