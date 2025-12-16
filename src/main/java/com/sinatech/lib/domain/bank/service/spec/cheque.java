package com.sinatech.lib.domain.bank.service.spec;

import com.rahand.sina.integration.provider.yaghut.proxy.mobapp.LoginResponseBean;
import com.sinatech.lib.domain.bank.dto.*;

public interface cheque {
    TokenResponse getToken(TokenRequest tokenRequest);
    TokenResponse getCurrentToken(TokenRequest tokenRequest);
    ChequeInquiryResponse chequeInquiry(ChequeInquiryRequest chequeInquiryRequest);
    ChequeAcceptOrRejectResponse chequeAcceptOrReject(ChequeAcceptOrRejectRequest request);

}
