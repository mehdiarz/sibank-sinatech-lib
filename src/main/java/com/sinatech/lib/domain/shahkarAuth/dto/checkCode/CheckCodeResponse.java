package com.sinatech.lib.domain.shahkarAuth.dto.checkCode;

import com.rahand.common.dto.BaseResponse;

public class CheckCodeResponse extends BaseResponse {

    private String UUIDInput;
    private String otpInput;
    private String status;
    private String rejectInfo;
    private String access_token;

    public CheckCodeResponse() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectInfo() {
        return rejectInfo;
    }

    public void setRejectInfo(String rejectInfo) {
        this.rejectInfo = rejectInfo;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
