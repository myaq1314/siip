package com.pay.siip.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Data
@Component
public final class ParamConfig {

    @Value("${common.appId}")
    private String appId;
    @Value("${common.merCode}")
    private String merCode;
    @Value("${common.subMerCode}")
    private String subMerCode;
    @Value("${common.appPrivateKey}")
    private String appPrivateKey;
    @Value("${common.appPublicKey}")
    private String appPublicKey;
    @Value("${common.siipPublicKey}")
    private String siipPublicKey;
    @Value("${common.version}")
    private String version;
    @Value("${common.charset}")
    private String charset;
    @Value("${common.signType}")
    private String signType;
    @Value("${common.ServerUrl}")
    private String ServerUrl;

    @Value("${common.socketTimeout}")
    private int socketTimeout;
    @Value("${common.connectTimeout}")
    private int connectTimeout;

}
