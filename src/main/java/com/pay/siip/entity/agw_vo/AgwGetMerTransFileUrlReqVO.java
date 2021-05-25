package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.siip.constant.DateConstant;
import com.pay.siip.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

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
public class AgwGetMerTransFileUrlReqVO extends AgwBodyReqVO {

    /**
     * 交易日期，格式 yyyyMMdd，必填
     */
    @JSONField(name = "trans_date")
    private String transDate;

    public AgwGetMerTransFileUrlReqVO(Date transDate) {
        this.transDate = DateUtil.convertToText(transDate, DateConstant.DATE_SIMPLE());
    }
}