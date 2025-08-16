package com.sinatech.lib.domain.shahkarAuth.dto;

import com.rahand.common.dto.BaseRequest;
import lombok.Builder;

public class ShahkarRequest extends BaseRequest {
    private String nationalCode;
    private String phone;

    public ShahkarRequest() {
    }

    @Builder
    public ShahkarRequest(String nationalCode, String phone) {
        this.nationalCode = nationalCode;
        this.phone = phone;
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
}
