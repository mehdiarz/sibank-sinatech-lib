package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseRequest;
import com.rahand.common.dto.BaseResponse;

public class ChequeInquiryRequest extends BaseRequest{

    @JsonProperty("sayadId")
    private String sayadId;

    @JsonProperty("holderCif")
    private String holderCif;

    @JsonProperty("callerInfo")
    private CallerInfo callerInfo;

    // Constructors
    public ChequeInquiryRequest() {}

    public ChequeInquiryRequest(String sayadId, String holderCif, CallerInfo callerInfo) {
        this.sayadId = sayadId;
        this.holderCif = holderCif;
        this.callerInfo = callerInfo;
    }

    // Getters and Setters
    public String getSayadId() {
        return sayadId;
    }

    public void setSayadId(String sayadId) {
        this.sayadId = sayadId;
    }

    public String getHolderCif() {
        return holderCif;
    }

    public void setHolderCif(String holderCif) {
        this.holderCif = holderCif;
    }

    public CallerInfo getCallerInfo() {
        return callerInfo;
    }

    public void setCallerInfo(CallerInfo callerInfo) {
        this.callerInfo = callerInfo;
    }
}