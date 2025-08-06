package com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms;

import com.rahand.common.dto.BaseResponse;
import lombok.Builder;

import java.util.List;

public class ShahkarResponse extends BaseResponse {
    private String nationalCode;
    private String phone;
    private String status;
    private List<UUIDResponse> responseUUID;

    public ShahkarResponse() {
    }

    @Builder
    public ShahkarResponse(String nationalCode, String phone, String status, List<UUIDResponse> responseUUID) {
        this.nationalCode = nationalCode;
        this.phone = phone;
        this.status = status;
        this.responseUUID = responseUUID;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UUIDResponse> getResponseUUID() {
        return responseUUID;
    }

    public void setResponseUUID(List<UUIDResponse> responseUUID) {
        this.responseUUID = responseUUID;
    }
}
