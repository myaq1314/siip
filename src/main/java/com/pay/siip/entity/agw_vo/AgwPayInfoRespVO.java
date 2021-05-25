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
 * date : 2021-05-24
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AgwPayInfoRespVO implements IBaseEntity {

    @JSONField(name = "pay_type")
    private String payType = "";

    @JSONField(name = "pay_time")
    private String payTime = "";

    @JSONField(name = "pay_no")
    private String payNo = "";

    @JSONField(name = "channel_order_no")
    private String channelOrderNo = "";

    @JSONField(name = "pay_amt")
    private int payAmt = 0;

    @JSONField(name = "discount_amt")
    private int discountAmt = 0;

    private int fee = 0;

    @JSONField(name = "jf_amt")
    private int jfAmt = 0;

    @JSONField(name = "trans_date")
    private String transDate = "";

}
