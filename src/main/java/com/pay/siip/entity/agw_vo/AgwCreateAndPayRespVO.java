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
public class AgwCreateAndPayRespVO extends AgwBodyRespVO {

    @JSONField(name = "qrcode_token")
    protected String qrcodeToken;

    /**
     * 订单号，32长度，必填，外部商户系统订单号
     */
    @JSONField(name = "ext_order_no")
    private String extOrderNo;

    /**
     * 行业应用平台系统订单号，非必填
     */
    @JSONField(name = "main_order_no")
    private String mainOrderNo;

    /**
     * 终端编号，15长度，必填，默认填0，传其它值银联系付款码会报错
     */
    @JSONField(name = "termcode")
    private String termCode;

    /**
     * 下单时间，非必填，格式 yyyyMMddHHmmss
     */
    @JSONField(name = "order_time")
    private String orderTime;

    /**
     * 订单总金额，必填，单位分
     */
    @JSONField(name = "total_amt")
    private int totalAmt;

    /**
     * 不打折金额，11长度，必填，不打折金额，必须 >=0，且<= totalAmt，当前版本只支持默认0，单位分
     */
    @JSONField(name = "no_discount_amt")
    private int noDiscountAmt;

    /**
     * 订单状态，WAITPAY 待支付，SUCCESS 已支付，CANCEL 已撤销， REFUND 已退款， CLOSE 订单关闭
     */
    @JSONField(name = "order_status")
    private String orderStatus;

    /**
     * 支付状态，P 支付中，S 已支付，F 支付失败
     */
    @JSONField(name = "pay_status")
    private String payStatus;

    /**
     * 交易摘要，64长度，不必填，备注说明交易信息，将显示在收银台页面备注项目
     */
    private String remark;

    /**
     * 自定义附加参数，100长度，不必填，回调通知和支付结果查询会原样数据返回
     */
    @JSONField(name = "add_data")
    private String addData;

    /**
     * 支付信息，仅pay_status为S时有值
     */
    @JSONField(name = "pay_info")
    private AgwPayInfoRespVO payInfo;

    /**
     * 平台分配的商户号，长度15，接入应用为服务商时非必填，使用 sub_mercode字段
     */
    @JSONField(name = "mercode")
    private String merCode;

    @JSONField(name = "mername")
    private String merName;

    @JSONField(name = "ext_user_id")
    private String extUserId;

    @JSONField(name = "ext_unionid")
    private String extUnionId;


}