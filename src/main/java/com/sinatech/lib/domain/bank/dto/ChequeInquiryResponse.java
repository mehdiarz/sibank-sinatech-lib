package com.sinatech.lib.domain.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rahand.common.dto.BaseResponse;

import java.util.ArrayList;
import java.util.List;
public class ChequeInquiryResponse extends BaseResponse {

    @JsonProperty("ownersInfo")
    private List<OwnerInfo> ownersInfo;

    @JsonProperty("onGoing")
    private Long onGoing;

    @JsonProperty("blocked")
    private Integer blocked;

    // Constructors
    public ChequeInquiryResponse() {
        this.ownersInfo = new ArrayList<>();
    }

    // Getters and Setters
    public List<OwnerInfo> getOwnersInfo() {
        return ownersInfo;
    }

    public void setOwnersInfo(List<OwnerInfo> ownersInfo) {
        this.ownersInfo = ownersInfo;
    }

    public Long getOnGoing() {
        return onGoing;
    }

    public void setOnGoing(Long onGoing) {
        this.onGoing = onGoing;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    // Helper method
    public void addOwnerInfo(OwnerInfo ownerInfo) {
        if (this.ownersInfo == null) {
            this.ownersInfo = new ArrayList<>();
        }
        this.ownersInfo.add(ownerInfo);
    }
}