package com.sinatech.lib.config;

import com.rahand.common.constant.CommonErrorMessage;
import com.rahand.common.exception.CustomRuntimeException;
import com.rahand.common.util.CommonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class IntegrationLibConfig {

    private Properties mainProperties;
    private static IntegrationLibConfig instance = null;
    private static Long createDate = 0L;

    private IntegrationLibConfig() throws IOException {

        mainProperties = new Properties();
        ClassLoader mainClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream mainInput = mainClassLoader.getResourceAsStream("sinatech-integration.properties");
        mainProperties.load(mainInput);

    }

    public static IntegrationLibConfig getInstance() {

        if (instance == null || (new Date()).getTime() - createDate > 300000) {
            try {
                instance = new IntegrationLibConfig();
            } catch (IOException e) {
                CommonUtil.logError(IntegrationLibConfig.class.getSimpleName(), "getInstance",
                        "Error in load library config file" + "Exception : " + e.getMessage());
                throw new CustomRuntimeException(CommonErrorMessage.SERVER_EXCEPTION_MESSAGE);
            }
            createDate = (new Date()).getTime();
        }
        return instance;
    }

    public Properties getMainProperties() {
        return mainProperties;
    }

    public static String getProperty(String key) {

        String property = getInstance().getMainProperties().getProperty(key);
        if (property == null) {
            CommonUtil.logError(IntegrationLibConfig.class.getSimpleName(), "getProperty", String.format("Not found property in config file - property : %s", key));
        }
        return property;
    }

}
