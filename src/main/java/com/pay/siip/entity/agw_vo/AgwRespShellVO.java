package com.pay.siip.entity.agw_vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.pay.siip.entity.IBaseEntity;
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
public class AgwRespShellVO<T extends AgwBodyRespVO> implements IBaseEntity {

    @JSONField(name = "AgwHead")
    private AgwHeadRespVO agwHead;

    @JSONField(name = "AgwBody")
    private T agwBody;

}
