package com.sinatech.lib.domain.shahkarAuth.dto;

import com.rahand.common.dto.BaseRequest;
import lombok.Builder;

public class CheckCodeRequest extends BaseRequest {

    private String UUIDInput;
    private String otpInput;

    public CheckCodeRequest() {}

    @Builder
    public CheckCodeRequest(String UUIDInput, String otpInput) {
        this.UUIDInput = UUIDInput;
        this.otpInput = otpInput;
    }

    public String getUUIDInput() {
        return UUIDInput;
    }

    public void setUUIDInput(String UUIDInput) {
        this.UUIDInput = UUIDInput;
    }

    public String getOtpInput() {
        return otpInput;
    }

    public void setOtpInput(String otpInput) {
        this.otpInput = otpInput;
    }
}
