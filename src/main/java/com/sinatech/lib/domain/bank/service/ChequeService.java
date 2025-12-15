package com.sinatech.lib.domain.bank.service;

import com.rahand.common.dto.ResponseDto;
import com.sinatech.lib.domain.bank.dto.ChequeAcceptOrRejectRequest;
import com.sinatech.lib.domain.bank.dto.ChequeAcceptOrRejectResponse;
import com.sinatech.lib.domain.bank.dto.ChequeInquiryRequest;
import com.sinatech.lib.domain.bank.dto.ChequeInquiryResponse;
import com.sinatech.lib.domain.bank.service.spec.cheque;

public class ChequeService {
    private cheque chequeService;

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
