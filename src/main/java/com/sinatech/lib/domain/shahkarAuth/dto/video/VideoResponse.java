package com.sinatech.lib.domain.shahkarAuth.dto.video;

import com.rahand.common.dto.BaseResponse;

public class VideoResponse extends BaseResponse {

    private String status;
    private String rejectInfo;

    public VideoResponse() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectInfo() {
        return rejectInfo;
    }

    public void setRejectInfo(String rejectInfo) {
        this.rejectInfo = rejectInfo;
    }
}
