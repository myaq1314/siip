package com.pay.siip.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.pay.siip.constant.ParamConstant;
import com.pay.siip.enums.TransactionCodeDictEnum;
import com.pay.siip.entity.agw_vo.AgwBodyReqVO;
import com.pay.siip.entity.agw_vo.AgwBodyRespVO;
import com.pay.siip.entity.agw_vo.AgwCreateAndPayRespVO;
import com.pay.siip.entity.agw_vo.AgwHeadReqVO;
import com.pay.siip.entity.agw_vo.AgwPreCreateRespVO;
import com.pay.siip.entity.agw_vo.AgwRefundRespVO;
import com.pay.siip.entity.agw_vo.AgwReqShellVO;
import com.pay.siip.entity.agw_vo.AgwRespShellVO;
import com.pay.siip.exceptions.CommonException;
import com.pay.siip.validate.EmptyAssert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-05-24
 * email 916419307@qq.com
 */
public final class HttpUtil extends HttpClientUtil {

    private static final Map<String, String> headMap;

    static {
        headMap = new HashMap<>();
        headMap.put("Content-type", "application/json");
    }

    public static <T extends AgwBodyReqVO> AgwRespShellVO<? extends AgwBodyRespVO> siipPost(TransactionCodeDictEnum transactionCode,
                                                                                            T agwBody) {
        EmptyAssert.allNotNull(transactionCode, agwBody);

        // 计算签名
        AgwHeadReqVO agwHead = new AgwHeadReqVO(transactionCode.key);
        SignatureUtil.signature(agwHead, agwBody);
        // 拼接请求体
        AgwReqShellVO<T> reqShellVO = new AgwReqShellVO<>(agwHead, agwBody);

        // 发送请求，获取响应
        String respJson = HttpClientUtil.doPost(ParamConstant.getServerUrl(), JSON.toJSONString(reqShellVO), headMap);
        System.out.println(respJson);
        EmptyAssert.isNotBlank(respJson);
        SignatureUtil.check(respJson);

        AgwRespShellVO<? extends AgwBodyRespVO> respShellVO = convert(transactionCode, respJson);
        EmptyAssert.isNotNull(respShellVO);
        assert respShellVO != null;

        return respShellVO;
    }

    private static AgwRespShellVO<? extends AgwBodyRespVO> convert(TransactionCodeDictEnum transactionCode,
                                                                   String respJson) {
        try {
            switch (transactionCode) {
                case SIIP_ORDER_PRE_CREATE:
                    return JSONObject.parseObject(respJson, new TypeReference<AgwRespShellVO<AgwPreCreateRespVO>>() {
                    });
                case SIIP_ORDER_CREATE_AND_PAY:
                case SIIP_ORDER_RESULT_QUERY:
                    return JSONObject.parseObject(respJson, new TypeReference<AgwRespShellVO<AgwCreateAndPayRespVO>>() {
                    });
                case SIIP_ORDER_REFUND:
                    return JSONObject.parseObject(respJson, new TypeReference<AgwRespShellVO<AgwRefundRespVO>>() {
                    });
                case SIIP_ORDER_REFUND_QUERY:
                    break;
                case SIIP_GET_MER_TRANS_FILE_URL:
                    break;
                case SIIP_UNI_ORDER_CREATE:
                    break;
                case SIIP_UNI_ORDER_CLOSE:
                    break;
                case SIIP_QNY_AUTH_CODE_QUERY:
                    break;
            }
            return null;
        } catch (Exception e) {
            throw new CommonException("响应文本转换响应实体异常");
        }
    }
}
