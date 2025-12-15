package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChequeAcceptOrRejectResponse extends BaseResponse {
    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("trackingId")
    private String trackingId;

    @JsonProperty("errorCode")
    private String errorCode;

    // Constructors
    public ChequeAcceptOrRejectResponse() {}

    public ChequeAcceptOrRejectResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
