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
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AgwReqShellVO<T extends AgwBodyReqVO> implements IBaseEntity {

    @JSONField(name = "AgwHead")
    private AgwHeadReqVO agwHead;

    @JSONField(name = "AgwBody")
    private T agwBody;

}
