package com.sinatech.lib.domain.shahkarAuth.dto.video;

import com.rahand.common.dto.BaseRequest;
import lombok.Builder;

import java.io.File;

public class VideoRequest extends BaseRequest {

    private File personVideo;

    public VideoRequest() {}

    @Builder
    public VideoRequest(File personVideo) {
        this.personVideo = personVideo;
    }

    public File getPersonVideo() {
        return personVideo;
    }

    public void setPersonVideo(File personVideo) {
        this.personVideo = personVideo;
    }
}
