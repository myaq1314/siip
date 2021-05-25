package com.pay.siip.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pay.siip.constant.ParamConstant;
import com.pay.siip.entity.agw_vo.AgwBodyReqVO;
import com.pay.siip.entity.agw_vo.AgwHeadReqVO;
import com.pay.siip.exceptions.CommonException;
import com.pay.siip.validate.EmptyAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author : czh
 * description :
 * date : 2021-05-23
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public class SignatureUtil {

    public static void signature(AgwHeadReqVO headVO, AgwBodyReqVO bodyVO) {
        // 报文头按照字母顺序从小到大排序
        String headJson = JSON.toJSONString(headVO, SerializerFeature.MapSortField);
        // 报文体按字母顺序从小到大排序
        String reqJson = JSON.toJSONString(bodyVO, SerializerFeature.MapSortField);
        // 拼接签名原始数据
        String signatureData = String.format("head=%s&body=%s", headJson, reqJson);
        // 计算签名
        String signature = sign(signatureData, ParamConstant.getAppPrivateKey());
        headVO.setSignature(signature);
    }

    public static String sign(String data, String privateKey) {
        EmptyAssert.allNotBlank(data, privateKey);

        try {
            // 解密由base64编码的私钥
            byte[] keyBytes = Base64.decodeBase64(privateKey);

            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance("SHA256withRSA", new BouncyCastleProvider());
            signature.initSign(priKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            throw new CommonException("计算签名异常");
        }
    }

    public static void check(String respJson) {
        JSONObject retMsg = JSONObject.parseObject(respJson);

        JSONObject retHead = retMsg.getJSONObject("AgwHead");
        String signature = retHead.getString("signature"); //从报文头取出签名数据
        retHead.remove("signature");

        JSONObject retBody = retMsg.getJSONObject("AgwBody");

        // 拼接签名原始数据
        String signatureData = String.format("head=%s&body=%s",
                JSON.toJSONString(retHead, SerializerFeature.MapSortField),
                JSON.toJSONString(retBody, SerializerFeature.MapSortField));
        verify(signatureData, ParamConstant.getSiipPublicKey(), signature);
    }

    public static void verify(String data, String publicKey, String sign) {
        EmptyAssert.allNotBlank(data, publicKey, sign);

        boolean verify;
        try {
            // 解密由base64编码的公钥
            byte[] keyBytes = Base64.decodeBase64(publicKey);

            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance("SHA256withRSA", new BouncyCastleProvider());
            signature.initVerify(pubKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));

            verify = signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            throw new CommonException("校验签名异常");
        }

        if (!verify) {
            throw new CommonException("签名错误，请联系管理员");
        }
    }
}
