package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : czh
 * description :
 * date : 2021-05-24
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgwRefundReqVO extends AgwBodyReqVO {

    @JSONField(name = "main_order_no")
    private String mainOrderNo;

    @JSONField(name = "ext_refund_no")
    private String extRefundNo;

    @JSONField(name = "term_ip")
    private String termIp;

    @JSONField(name = "term_code")
    private String termCode;

    private String operator;

    @JSONField(name = "refund_amt")
    private int refundAmt;

    @JSONField(name = "refund_reason")
    private String refundReason;

    public AgwRefundReqVO(String mainOrderNo,
                          int refundAmt,
                          String refundReason) {
        this.mainOrderNo = mainOrderNo;
        this.extRefundNo = String.valueOf(System.nanoTime());
        this.termIp = "";
        this.termCode = "0";
        this.operator = "0";
        this.refundAmt = refundAmt;
        this.refundReason = refundReason;
    }
}