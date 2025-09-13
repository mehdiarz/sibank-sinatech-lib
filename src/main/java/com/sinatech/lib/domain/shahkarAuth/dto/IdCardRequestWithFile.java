package com.sinatech.lib.domain.shahkarAuth.dto;

import com.rahand.common.dto.BaseRequest;
import lombok.*;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class IdCardRequestWithFile extends BaseRequest {
    private File idCardImg;
    private File behindIdCardImg;
    private String birthDate;
}
