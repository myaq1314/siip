package com.pay.siip.constant;

import com.pay.siip.config.ParamConfig;
import com.pay.siip.util.SpringFactoryUtil;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
public final class ParamConstant {

    private static final ParamConfig paramConfig;

    static {
        paramConfig = SpringFactoryUtil.getBean(ParamConfig.class);
    }

    public static String getAppId() {
        return paramConfig.getAppId();
    }

    public static String getMerCode() {
        return paramConfig.getMerCode();
    }

    public static String getSubMerCode() {
        return paramConfig.getSubMerCode();
    }

    public static String getAppPrivateKey() {
        return paramConfig.getAppPrivateKey();
    }

    public static String getAppPublicKey() {
        return paramConfig.getAppPublicKey();
    }

    public static String getSiipPublicKey() {
        return paramConfig.getSiipPublicKey();
    }

    public static String getVersion() {
        return paramConfig.getVersion();
    }

    public static String getCharset() {
        return paramConfig.getCharset();
    }

    public static String getSignType() {
        return paramConfig.getSignType();
    }

    public static String getServerUrl() {
        return paramConfig.getServerUrl();
    }

    public static int getSocketTimeout() {
        return paramConfig.getSocketTimeout();
    }

    public static int getConnectTimeout() {
        return paramConfig.getConnectTimeout();
    }
}
