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
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgwPreCreateReqVO extends AgwBodyReqVO {

    /**
     * 订单号，32长度，必填，外部商户系统订单号
     */
    @JSONField(name = "ext_order_no")
    private String extOrderNo;

    /**
     * 终端编号，15长度，必填，默认填0，传其它值银联系付款码会报错
     */
    @JSONField(name = "termcode")
    private String termCode;

    /**
     * 终端IP，15长度，非必填，发起交易的终端IP
     */
    @JSONField(name = "term_ip")
    private String termIp;

    /**
     * 收银员，20长度，必填，必须是行业应用平台注册登记的操作员账号，否则填0，
     */
    @JSONField(name = "operator")
    private String operator;

    /**
     * 订单总金额，11长度，必填，订单金额，金额必须大于0，单位分
     */
    @JSONField(name = "total_amt")
    private int totalAmt;

    /**
     * 不打折金额，11长度，必填，不打折金额，必须 >=0，且<= totalAmt，当前版本只支持默认0，单位分
     */
    @JSONField(name = "no_discount_amt")
    private int noDiscountAmt;

    /**
     * 交易信息，32长度，不必填，本版本暂不显示处理
     */
    @JSONField(name = "trade_info")
    private String tradeInfo;

    /**
     * 交易详情，128长度，不必填，本版本暂不显示处理
     */
    @JSONField(name = "trade_detail")
    private String tradeDetail;

    /**
     * 二维码支付有效时间，5长度，必填，二维码必须在有效期内完成支付。0表示行业应用平台系统默认 5分钟，单位秒
     */
    @JSONField(name = "expires_in")
    private int expiresIn;

    /**
     * 交易摘要，64长度，不必填，备注说明交易信息，将显示在收银台页面备注项目
     */
    private String remark;

    /**
     * 前端跳转地址，128长度，非必填，支付完成后，前端页面跳转地址
     */
    @JSONField(name = "redirect_url")
    private String redirectUrl;

    /**
     * 消息通知，128长度，不必填，支付成功结果通知地址
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;

    /**
     * 是否可用营销优惠产品，1长度，不必填，预留字段，默认N，Y是，N否
     */
    @JSONField(name = "is_marketing")
    private String isMarketing;

    /**
     * 商品SKU编码，255长度，不必填，预留字段，json格式，商品SKU编码与商品数量按照键值对的方式填写
     * 例如："SKU编码1":"20","SKU编码2":"10"
     */
    @JSONField(name = "goods_sku")
    private String goodsSku;

    /**
     * 是否支持信用卡付款，1长度，不必填，预留字段，默认Y，Y是，N否
     */
    @JSONField(name = "is_credit_card")
    private String isCreditCard;

    /**
     * 自定义附加参数，100长度，不必填，回调通知和支付结果查询会原样数据返回
     */
    @JSONField(name = "add_data")
    private String addData;

    public AgwPreCreateReqVO(int totalAmt,
                             int expiresIn,
                             String remark,
                             String redirectUrl,
                             String notifyUrl,
                             String addData) {
        this.extOrderNo = String.valueOf(System.nanoTime());
        this.termCode = "0";
        this.termIp = "";
        this.operator = "0";
        this.totalAmt = totalAmt;
        this.noDiscountAmt = 0;
        this.tradeInfo = "";
        this.tradeDetail = "";
        this.expiresIn = expiresIn;
        this.remark = remark;
        this.redirectUrl = redirectUrl;
        this.notifyUrl = notifyUrl;
        this.isMarketing = "Y";
        this.goodsSku = "";
        this.isCreditCard = "Y";
        this.addData = addData;
    }
}
