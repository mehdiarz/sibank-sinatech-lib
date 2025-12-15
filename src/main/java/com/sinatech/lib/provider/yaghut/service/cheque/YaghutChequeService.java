package com.sinatech.lib.provider.yaghut.service.cheque;

import com.rahand.sina.integration.provider.yaghut.proxy.mobapp.ContextEntry;
import com.rahand.sina.integration.provider.yaghut.proxy.mobapp.LoginResponseBean;
import com.rahand.sina.integration.provider.yaghut.service.cheque.YaghutChequeProxy;
import com.rahand.sina.integration.provider.yaghut.service.user.YaghutUserService;
import com.rahand.sina.integration.provider.yaghut.util.YaghutUtil;
import com.sinatech.lib.domain.bank.service.spec.cheque;

public class YaghutChequeService {
    private YaghutUserService yaghutUserService = YaghutUserService.getInstance();
    private YaghutChequeProxy yaghutChequeProxy = new YaghutChequeProxy();

    public LoginResponseBean login(String userId) {
        LoginResponseBean loginResponse = this.yaghutUserService.getSessionId(userId);
        ContextEntry[] context = YaghutUtil.createYaghutSessionContext(loginResponse);
        return loginResponse;
    }

}
