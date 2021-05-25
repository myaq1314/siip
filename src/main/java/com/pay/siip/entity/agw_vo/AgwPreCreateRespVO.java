package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AgwPreCreateRespVO extends AgwBodyRespVO {

    @JSONField(name = "qrcode_token")
    protected String qrcodeToken;

    /**
     * 收款地址，128长度，必填，二维码URL
     */
    @JSONField(name = "pay_url")
    private String payUrl;

    /**
     * 二维码有效时间，5长度，必填，单位秒
     */
    @JSONField(name = "expires_in")
    private int expiresIn;

}
