package com.sinatech.lib.domain.bank.service.spec;

import com.rahand.sina.integration.provider.yaghut.proxy.mobapp.LoginResponseBean;
import com.sinatech.lib.domain.bank.dto.ChequeAcceptOrRejectRequest;
import com.sinatech.lib.domain.bank.dto.ChequeAcceptOrRejectResponse;
import com.sinatech.lib.domain.bank.dto.ChequeInquiryRequest;
import com.sinatech.lib.domain.bank.dto.ChequeInquiryResponse;

public interface cheque {
    public ChequeInquiryResponse chequeInquiry(ChequeInquiryRequest chequeInquiryRequest);
    ChequeAcceptOrRejectResponse chequeAcceptOrReject(ChequeAcceptOrRejectRequest request);

}
