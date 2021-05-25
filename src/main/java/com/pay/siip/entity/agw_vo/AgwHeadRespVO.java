package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.siip.entity.IBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class AgwHeadRespVO implements IBaseEntity {

    /**
     * 交易码，长度30
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
    @JSONField(name = "resp_time")
    private String respTime;

    /**
     * 签名方法，长度1000
     */
    private String signature;

    @JSONField(name = "resp_code")
    private String respCode;

    @JSONField(name = "resp_msg")
    private String respMsg;
}
