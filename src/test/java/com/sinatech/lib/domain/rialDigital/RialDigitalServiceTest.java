//package com.sinatech.lib.domain.rialDigital;
//
//import com.rahand.common.dto.ResponseDto;
//import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenReponse;
//import com.sinatech.lib.domain.rialDigital.dto.RialDigitalGetTokenRequest;
//import com.sinatech.lib.domain.rialDigital.service.RialDigitalService;
//import com.sinatech.lib.domain.rialDigital.service.spec.RialDigital;
//import org.junit.Test;
//
//public class RialDigitalServiceTest {
//
//    @Test
//    public void getToken(){
//        RialDigitalGetTokenRequest rialDigitalGetTokenRequest = new RialDigitalGetTokenRequest();
//        rialDigitalGetTokenRequest.setUserId("9910433274");
//        ResponseDto<RialDigitalGetTokenReponse> res = new RialDigitalService().getToken(rialDigitalGetTokenRequest);
//        System.out.println(res.getResponseData().getToken());
//    }
//}
