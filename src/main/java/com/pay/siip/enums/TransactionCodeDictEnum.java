package com.pay.siip.enums;

import com.pay.siip.enums.parent.IKeyEnum;
import lombok.Getter;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
public enum TransactionCodeDictEnum implements IKeyEnum<String> {
    /**
     * 请求收银二维码
     * 用于商户端出示一码付二维码，供用户扫码并完成支付
     */
    SIIP_ORDER_PRE_CREATE("siip_order_precreate"),
    /**
     * 用户被扫订单支付
     * 用于扫码用户微信/支付宝/黔农云
     */
    SIIP_ORDER_CREATE_AND_PAY("siip_order_create_and_pay"),
    /**
     * 查询订单支付结果
     * 用于查询订单支付状态
     */
    SIIP_ORDER_RESULT_QUERY("siip_order_result_query"),
    /**
     * 订单退款
     * 订单退款请求，接口返回成功仅表示退款请求受理接收成功，退款结果以退款查询接口为准
     */
    SIIP_ORDER_REFUND("siip_order_refund"),
    /**
     * 订单退款状态查询
     * 查询订单退款结果
     */
    SIIP_ORDER_REFUND_QUERY("siip_order_refund_query"),
    /**
     * 请求对账文件下载地址
     * 获取对账文件下载地址连接
     */
    SIIP_GET_MER_TRANS_FILE_URL("siip_get_mer_trans_file_url"),
    /**
     * 统一下单接口
     * 适用于商户小程序、APP支付
     */
    SIIP_UNI_ORDER_CREATE("siip_uni_order_create"),
    /**
     * 统一订单关闭接口
     * 对 siip_uni_order_create 统一下单接口 创建的订单进行关闭
     */
    SIIP_UNI_ORDER_CLOSE("siip_uni_order_close"),
    /**
     * 查询付款码是否黔农云APP付款码
     * 适用于判断客户是否黔农云用户
     */
    SIIP_QNY_AUTH_CODE_QUERY("siip_qny_auth_code_query"),

    // 占位符
    ;

    @Getter
    public final String key;

    TransactionCodeDictEnum(String key) {
        this.key = key;
    }
}
