package com.sinatech.lib.domain.shahkarAuth.dto;

public class SimpleResponse {
    private boolean status;
    private String message;

    public SimpleResponse() {
    }

    public SimpleResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
