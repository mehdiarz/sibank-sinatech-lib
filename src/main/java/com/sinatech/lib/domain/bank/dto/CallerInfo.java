package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallerInfo {

    @JsonProperty("callerBranchCode")
    private String callerBranchCode;

    // Constructors
    public CallerInfo() {}

    public CallerInfo(String callerBranchCode) {
        this.callerBranchCode = callerBranchCode;
    }

    // Getters and Setters
    public String getCallerBranchCode() {
        return callerBranchCode;
    }

    public void setCallerBranchCode(String callerBranchCode) {
        this.callerBranchCode = callerBranchCode;
    }
}