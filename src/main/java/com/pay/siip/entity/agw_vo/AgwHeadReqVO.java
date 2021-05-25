package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.siip.constant.DateConstant;
import com.pay.siip.constant.ParamConstant;
import com.pay.siip.entity.IBaseEntity;
import com.pay.siip.util.DateUtil;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "农信接口 基本请求头 数据实体")
public class AgwHeadReqVO implements IBaseEntity {

    /**
     * 交易码，长度30
     *
     * @see com.pay.siip.enums.TransactionCodeDictEnum
     */
    @JSONField(name = "msg_code")
    private String msgCode;

    /**
     * 发起方交易流水 长度32，唯一
     */
    @JSONField(name = "seq_no")
    private String seqNo;

    /**
     * 交易时间 长度14，格式为yyyyMMddHHmmss，东八区24小时制
     */
    @JSONField(name = "req_time")
    private String reqTime;

    /**
     * 接口版本号，长度8， 默认 3.0.1
     */
    private String version;

    /**
     * 编码 长度10，UTF-8
     */
    private String charset = "UTF-8";

    /**
     * 平台分配给接入系统的接入ID，长度32
     */
    @JSONField(name = "app_id")
    private String appId;

    /**
     * 平台分配的商户号，长度15，接入应用为服务商时非必填，使用 sub_mercode字段
     */
    @JSONField(name = "mercode")
    private String merCode;

    /**
     * 接入应用为服务商时必填，长度15
     */
    @JSONField(name = "sub_mercode")
    private String subMerCode;

    /**
     * rsa 签名，长度18
     */
    @JSONField(name = "sign_type")
    private String signType = "rsa";

    /**
     * 签名方法，长度1000
     */
    private String signature;

    public AgwHeadReqVO(String msgCode) {
        this.msgCode = msgCode;
        this.seqNo = String.valueOf(System.nanoTime());
        this.reqTime = DateUtil.convertToText(new Date(), DateConstant.DATETIME_SIMPLE());
        this.version = ParamConstant.getVersion();
        this.charset = ParamConstant.getCharset();
        this.appId = ParamConstant.getAppId();
        this.merCode = ParamConstant.getMerCode();
        this.subMerCode = ParamConstant.getSubMerCode();
        this.signType = ParamConstant.getSignType();
    }
}
