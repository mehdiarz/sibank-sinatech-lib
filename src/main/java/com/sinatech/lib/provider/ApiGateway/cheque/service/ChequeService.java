package com.sinatech.lib.provider.ApiGateway.cheque.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.dto.ServiceLogsDto;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;
import com.sinatech.lib.domain.bank.dto.*;
import com.sinatech.lib.domain.bank.service.spec.cheque;
import com.sinatech.lib.provider.ApiGateway.cheque.util.ChequeRestClient;
import okhttp3.Response;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChequeService implements cheque {

    private static final String BASE_URL = "http://10.17.105.41:38021";

    private final ObjectMapper objectMapper;

    public ChequeService() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ChequeInquiryResponse chequeInquiry(ChequeInquiryRequest chequeInquiryRequest) {
        String ENDPOINT = "/tg/pichak/cheque/external/lowairy/owner-cheque-sirius-with-name";

        ChequeInquiryResponse chequeInquiryResponse = new ChequeInquiryResponse();
        Map<String, String> requestMap = new LinkedHashMap<>();
        requestMap.put("sayadId", chequeInquiryRequest.getSayadId());
        requestMap.put("holderCif", chequeInquiryRequest.getHolderCif());
        CallerInfo callerInfo = chequeInquiryRequest.getCallerInfo();
        if (callerInfo != null) {
            try {
                String callerInfoJson = objectMapper.writeValueAsString(callerInfo);
                requestMap.put("callerInfo", callerInfoJson);
            } catch (Exception e) {
                throw new CustomRuntimeException("Failed to convert CallerInfo to JSON: " + e.getMessage());
            }
        } else {
            requestMap.put("callerInfo", "null");
        }

        String url = BASE_URL + ENDPOINT;
        Response response = ChequeRestClient.postRequest(url, requestMap);
        if (response.isSuccessful()) {

            String jsonResponse = ChequeRestClient.responseBodyToString(response);
            chequeInquiryResponse = CommonUtil.jsonToObject(jsonResponse, ChequeInquiryResponse.class);
        }
        else {
            String jsonResponse = ChequeRestClient.responseBodyToString(response);
            CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "chequeInquiry", jsonResponse, ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
                    .output(jsonResponse)
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
        return chequeInquiryResponse;
    }

    public ChequeAcceptOrRejectResponse chequeAcceptOrReject(ChequeAcceptOrRejectRequest chequeAcceptOrRejectRequest){
        String ENDPOINT = "/tg/pichak/cheque/external/accept-or-reject";

        ChequeAcceptOrRejectResponse chequeAcceptOrRejectResponse = new ChequeAcceptOrRejectResponse();
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("sayadId", chequeAcceptOrRejectRequest.getSayadId());
        requestMap.put("acceptDescription",  chequeAcceptOrRejectRequest.getAcceptDescription());
        requestMap.put("acceptorAgentCif",  chequeAcceptOrRejectRequest.getAcceptorAgentCif());
        requestMap.put("acceptorCif",  chequeAcceptOrRejectRequest.getAcceptorCif());
        CallerInfo callerInfo = chequeAcceptOrRejectRequest.getCallerInfo();
        if (callerInfo != null) {
            try {
                // ساخت Map برای callerInfo
                Map<String, Object> callerInfoMap = new LinkedHashMap<>();
                callerInfoMap.put("callerBranchCode", callerInfo.getCallerBranchCode());
                requestMap.put("callerInfo", callerInfoMap); // ارسال به صورت Map
            } catch (Exception e) {
                throw new CustomRuntimeException("Failed to convert CallerInfo to JSON: " + e.getMessage());
            }
        }
        requestMap.put("accept",  chequeAcceptOrRejectRequest.isAccept());

        String url = BASE_URL + ENDPOINT;
        Response response = ChequeRestClient.postRequest(url, requestMap);
        if (response.isSuccessful()) {

            String jsonResponse = ChequeRestClient.responseBodyToString(response);
            chequeAcceptOrRejectResponse = CommonUtil.jsonToObject(jsonResponse, ChequeAcceptOrRejectResponse.class);
        }
        else {
            String jsonResponse = ChequeRestClient.responseBodyToString(response);
            CommonUtil.logError(ChequeRestClient.class.getSimpleName(), "chequeAcceptOrReject", jsonResponse, ServiceLogsDto.builder()
                    .input(CommonUtil.toJson(requestMap))
                    .output(jsonResponse)
                    .build());
            throw new CustomRuntimeException(CommonErrorMessage.EXTERNAL_SERVICE_EXCEPTION_MESSAGE);
        }
        return chequeAcceptOrRejectResponse;
    }

}