package com.pay.siip.api;

import com.pay.siip.enums.TransactionCodeDictEnum;
import com.pay.siip.entity.agw_vo.AgwBodyRespVO;
import com.pay.siip.entity.agw_vo.AgwCreateAndPayReqVO;
import com.pay.siip.entity.agw_vo.AgwCreateAndPayRespVO;
import com.pay.siip.entity.agw_vo.AgwGetMerTransFileUrlReqVO;
import com.pay.siip.entity.agw_vo.AgwPreCreateReqVO;
import com.pay.siip.entity.agw_vo.AgwQueryReqVO;
import com.pay.siip.entity.agw_vo.AgwRefundReqVO;
import com.pay.siip.entity.agw_vo.AgwRespShellVO;
import com.pay.siip.entity.BaseResponse;
import com.pay.siip.util.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@RestController
@RequestMapping("/siip/order")
public class OrderController {

    private String extOrderNo;
    private String mainOrderNo;

    @PostMapping("/preCreate")
    public BaseResponse<AgwRespShellVO<?>> preCreate() {
        AgwPreCreateReqVO reqVO = new AgwPreCreateReqVO(1, 0, "测试", "", "", "测试附加数据");
        extOrderNo = reqVO.getExtOrderNo();
        AgwRespShellVO<?> respShellVO = HttpUtil.siipPost(TransactionCodeDictEnum.SIIP_ORDER_PRE_CREATE, reqVO);
        return BaseResponse.newSuccessResp(respShellVO);
    }

    @PostMapping("/createAndPay")
    public BaseResponse<AgwRespShellVO<?>> createAndPay() {
        AgwCreateAndPayReqVO reqVO = new AgwCreateAndPayReqVO(1, "134532408306067462", "", "测试", "测试附加数据");
        AgwRespShellVO<? extends AgwBodyRespVO> respShellVO = HttpUtil.siipPost(TransactionCodeDictEnum.SIIP_ORDER_CREATE_AND_PAY, reqVO);

        return BaseResponse.newSuccessResp(respShellVO);
    }

    @PostMapping("/queryOrderResult")
    public BaseResponse<AgwRespShellVO<?>> queryOrderResult() {
        AgwQueryReqVO reqVO = new AgwQueryReqVO("ext_order_no", extOrderNo);
        AgwRespShellVO<? extends AgwBodyRespVO> respShellVO = HttpUtil.siipPost(TransactionCodeDictEnum.SIIP_ORDER_RESULT_QUERY, reqVO);
        AgwCreateAndPayRespVO respVO = (AgwCreateAndPayRespVO) respShellVO.getAgwBody();
        mainOrderNo = respVO.getMainOrderNo();
        return BaseResponse.newSuccessResp(respShellVO);
    }

    @PostMapping("/refundOrder")
    public BaseResponse<AgwRespShellVO<?>> refundOrder() {
        AgwRefundReqVO reqVO = new AgwRefundReqVO(mainOrderNo, 1, "测试退款");
        AgwRespShellVO<? extends AgwBodyRespVO> respShellVO = HttpUtil.siipPost(TransactionCodeDictEnum.SIIP_ORDER_REFUND, reqVO);
        return BaseResponse.newSuccessResp(respShellVO);
    }

    @PostMapping("/getMerTransFileUrl")
    public BaseResponse<AgwRespShellVO<?>> getMerTransFileUrl() {
        AgwGetMerTransFileUrlReqVO reqVO = new AgwGetMerTransFileUrlReqVO(new Date());
        AgwRespShellVO<? extends AgwBodyRespVO> respShellVO = HttpUtil.siipPost(TransactionCodeDictEnum.SIIP_ORDER_REFUND, reqVO);
        return BaseResponse.newSuccessResp(respShellVO);
    }
}
