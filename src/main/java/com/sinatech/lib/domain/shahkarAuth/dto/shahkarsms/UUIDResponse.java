package com.sinatech.lib.domain.shahkarAuth.dto.shahkarsms;

public class UUIDResponse {
    private String randomUUID;

    public UUIDResponse() {
    }

    public UUIDResponse(String randomUUID) {
        this.randomUUID = randomUUID;
    }

    public String getRandomUUID() {
        return randomUUID;
    }

    public void setRandomUUID(String randomUUID) {
        this.randomUUID = randomUUID;
    }
}
