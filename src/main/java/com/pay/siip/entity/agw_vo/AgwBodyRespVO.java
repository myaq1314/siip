package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.siip.entity.IBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "农信接口 基本响应体 数据实体")
public class AgwBodyRespVO implements IBaseEntity {

    /**
     * 业务返回码，15长度，必填，0000000 或 00000 表示业务处理成功，其它表示失败
     */
    @ApiModelProperty(value = "业务返回码", required = true, example = "0000000")
    @JSONField(name = "result_code")
    protected String resultCode;

    /**
     * 业务返回信息，不限长度，必填，
     */
    @ApiModelProperty(value = "业务返回信息", required = true, example = "success")
    @JSONField(name = "result_msg")
    protected String resultMsg;

}
