package com.sinatech.lib.domain.bank.service;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.bank.dto.*;
import com.sinatech.lib.domain.bank.service.spec.cheque;

public class ChequeService {
    private cheque chequeService;

    public ResponseDto<TokenResponse> getToken(TokenRequest tokenRequest){
        ResponseDto<TokenResponse> response = new ResponseDto<>();

        TokenResponse tokenResponse = chequeService.getToken(tokenRequest);

        if(tokenResponse.isSessionExpired()){
            tokenResponse  = chequeService.getToken(tokenRequest);
        }

        if (tokenResponse.isError()){
            response.setError(true);
            response.setMessage(tokenResponse.getErrorMessage());
            response.setResponseData(tokenResponse);
        } else {
            response.setError(false);
            response.setResponseData(tokenResponse);
        }

        return response;
    }

    public ResponseDto<TokenResponse> getCurrentToken(TokenRequest tokenRequest){
        ResponseDto<TokenResponse> response = new ResponseDto<>();

        TokenResponse tokenResponse = chequeService.getCurrentToken(tokenRequest);

        if(tokenResponse.isSessionExpired()){
            tokenResponse  = chequeService.getCurrentToken(tokenRequest);
        }

        if (tokenResponse.isError()){
            response.setError(true);
            response.setMessage(tokenResponse.getErrorMessage());
            response.setResponseData(tokenResponse);
        } else {
            response.setError(false);
            response.setResponseData(tokenResponse);
        }

        return response;
    }

    public ResponseDto<ChequeInquiryResponse> chequeInquiry(ChequeInquiryRequest chequeInquiryRequest) {
        ResponseDto<ChequeInquiryResponse> response = new ResponseDto<>();

        ChequeInquiryResponse chequeInquiryResponse = chequeService.chequeInquiry(chequeInquiryRequest);

        if (chequeInquiryResponse.isSessionExpired()) {
            chequeInquiryResponse = chequeService.chequeInquiry(chequeInquiryRequest);
        }

        if (chequeInquiryResponse.isError()) {
            response.setError(true);
            response.setMessage(chequeInquiryResponse.getErrorMessage());
            response.setResponseData(chequeInquiryResponse);

        } else {
            response.setError(false);
            response.setResponseData(chequeInquiryResponse);
        }
        return response;

    }

    public ResponseDto<ChequeAcceptOrRejectResponse> chequeAcceptOrRejectResponseResponseDto(ChequeAcceptOrRejectRequest chequeAcceptOrRejectRequest) {
        ResponseDto<ChequeAcceptOrRejectResponse> response = new ResponseDto<>();

        ChequeAcceptOrRejectResponse chequeAcceptOrRejectResponse = chequeService.chequeAcceptOrReject(chequeAcceptOrRejectRequest);

        if (chequeAcceptOrRejectResponse.isSessionExpired()) {
            chequeAcceptOrRejectResponse = chequeService.chequeAcceptOrReject(chequeAcceptOrRejectRequest);
        }

        if (chequeAcceptOrRejectResponse.isError()) {
            response.setError(true);
            response.setMessage(chequeAcceptOrRejectResponse.getErrorMessage());
            response.setResponseData(chequeAcceptOrRejectResponse);
        }else  {
            response.setError(false);
            response.setResponseData(chequeAcceptOrRejectResponse);
        }
        return response;
    }
}
