package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OwnerInfo {

    @JsonProperty("bounced")
    private Long bounced;

    @JsonProperty("cleared")
    private Long cleared;

    @JsonProperty("idCode")
    private String idCode;

    @JsonProperty("idType")
    private String idType;

    @JsonProperty("name")
    private String name;

    // Constructors
    public OwnerInfo() {}

    public OwnerInfo(Long bounced, Long cleared, String idCode, String idType, String name) {
        this.bounced = bounced;
        this.cleared = cleared;
        this.idCode = idCode;
        this.idType = idType;
        this.name = name;
    }

    // Getters and Setters
    public Long getBounced() {
        return bounced;
    }

    public void setBounced(Long bounced) {
        this.bounced = bounced;
    }

    public Long getCleared() {
        return cleared;
    }

    public void setCleared(Long cleared) {
        this.cleared = cleared;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}