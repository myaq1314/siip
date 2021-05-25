package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class AgwRefundRespVO extends AgwBodyRespVO {

    @JSONField(name = "refund_order_no")
    private String refundOrderNo;

    @JSONField(name = "main_order_no")
    private String mainOrderNo;

    @JSONField(name = "refund_status")
    private String refundStatus;

    @JSONField(name = "msg")
    private String msg;

}