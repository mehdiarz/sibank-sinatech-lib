package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseRequest;

public class ChequeAcceptOrRejectRequest extends BaseRequest {
    @JsonProperty("sayadId")
    private String sayadId;

    @JsonProperty("acceptDescription")
    private String acceptDescription;

    @JsonProperty("acceptorAgentCif")
    private String acceptorAgentCif;

    @JsonProperty("acceptorCif")
    private String acceptorCif;

    @JsonProperty("callerInfo")
    private CallerInfo callerInfo;

    @JsonProperty("accept")
    private boolean accept;

    public String getSayadId() {
        return sayadId;
    }

    public void setSayadId(String sayadId) {
        this.sayadId = sayadId;
    }

    public String getAcceptDescription() {
        return acceptDescription;
    }

    public void setAcceptDescription(String acceptDescription) {
        this.acceptDescription = acceptDescription;
    }

    public String getAcceptorAgentCif() {
        return acceptorAgentCif;
    }

    public void setAcceptorAgentCif(String acceptorAgentCif) {
        this.acceptorAgentCif = acceptorAgentCif;
    }

    public String getAcceptorCif() {
        return acceptorCif;
    }

    public void setAcceptorCif(String acceptCif) {
        this.acceptorCif = acceptCif;
    }

    public CallerInfo getCallerInfo() {
        return callerInfo;
    }

    public void setCallerInfo(CallerInfo callerInfo) {
        this.callerInfo = callerInfo;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
