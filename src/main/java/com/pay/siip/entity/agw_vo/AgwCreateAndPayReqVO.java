package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : czh
 * description : 被扫码支付
 * date : 2021-05-24
 * email 916419307@qq.com
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AgwCreateAndPayReqVO extends AgwBodyReqVO {

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
     * 付款码类型：AUTO-后台自动识别
     */
    @JSONField(name = "pay_code_type")
    private String payCodeType;

    /**
     * 扫描用户二维码的支付码
     */
    @JSONField(name = "auth_code")
    private String authCode;

    /**
     * 消息通知，128长度，不必填，支付成功结果通知地址
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;

    /**
     * 交易摘要，64长度，不必填，备注说明交易信息，将显示在收银台页面备注项目
     */
    private String remark;

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

    public AgwCreateAndPayReqVO(int totalAmt,
                                String authCode,
                                String notifyUrl,
                                String remark,
                                String addData) {
        this.extOrderNo = String.valueOf(System.nanoTime());
        this.termCode = "0";
        this.termIp = "";
        this.operator = "0";
        this.totalAmt = totalAmt;
        this.noDiscountAmt = 0;
        this.payCodeType = "AUTO";
        this.authCode = authCode;
        this.notifyUrl = notifyUrl;
        this.remark = remark;
        this.isMarketing = "Y";
        this.goodsSku = "";
        this.isCreditCard = "Y";
        this.addData = addData;
    }
}