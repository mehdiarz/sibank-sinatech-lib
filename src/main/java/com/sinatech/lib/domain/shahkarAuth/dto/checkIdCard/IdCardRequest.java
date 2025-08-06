package com.sinatech.lib.domain.shahkarAuth.dto.checkIdCard;

import com.rahand.common.dto.BaseRequest;
import lombok.Builder;
import java.io.File;

public class IdCardRequest extends BaseRequest {
    private File idCardImg;
    private File behindIdCardImg;
    private String birthDate;

    public IdCardRequest() {}

    @Builder
    public IdCardRequest(File idCardImg, File behindIdCardImg, String birthDate) {
        this.idCardImg = idCardImg;
        this.behindIdCardImg = behindIdCardImg;
        this.birthDate = birthDate;
    }

    public File getIdCardImg() { return idCardImg; }

    public void setIdCardImg(File idCardImg) { this.idCardImg = idCardImg; }

    public File getBehindIdCardImg() { return behindIdCardImg; }

    public void setBehindIdCardImg(File behindIdCardImg) { this.behindIdCardImg = behindIdCardImg; }

    public String getBirthDate() { return birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}
