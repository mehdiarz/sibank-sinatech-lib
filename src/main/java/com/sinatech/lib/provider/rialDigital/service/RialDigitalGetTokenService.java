package com.sinatech.lib.provider.rialDigital.service;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenRequest;
import com.sinatech.lib.domain.rialDigital.service.spec.RialDigital;
import com.sinatech.lib.provider.rialDigital.constants.RialDigitalConstants;
import com.sinatech.lib.provider.rialDigital.util.RialDigitalRestClient;
import okhttp3.Response;
import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenReponse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RialDigitalGetTokenService implements RialDigital {

    public static boolean TOKEN_INVALID = true;
    private final Lock lock = new ReentrantLock();


    public RialDigitalGetTokenService()  {
    }

    private static class SingletonHolder {
        private final static RialDigitalGetTokenService INSTANCE = new RialDigitalGetTokenService();
    }

    public static RialDigitalGetTokenService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public RialDigitalGetTokenReponse getToken(RialDigitalGetTokenRequest rialDigitalGetTokenRequest) {
        RialDigitalGetTokenReponse rialDigitalGetTokenReponse = new RialDigitalGetTokenReponse();
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("userId", rialDigitalGetTokenRequest.getUserId());

        String url = RialDigitalConstants.BASE_URL + "api/getToken/";
        Response response = RialDigitalRestClient.postRequest(url, requestMap);
        if (response.isSuccessful()) {

            String jsonResponse = RialDigitalRestClient.responseBodyToString(response);
            rialDigitalGetTokenReponse = CommonUtil.jsonToObject(jsonResponse, RialDigitalGetTokenReponse.class);
        }
        else {
            String jsonResponse = RialDigitalRestClient.responseBodyToString(response);
            CommonUtil.logError(RialDigitalGetTokenService.class.getSimpleName(), "getToken", jsonResponse, ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
                    .output(jsonResponse)
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
        return rialDigitalGetTokenReponse;

    }
}
