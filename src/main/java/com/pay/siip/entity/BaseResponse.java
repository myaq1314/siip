package com.pay.siip.entity;

import com.pay.siip.enums.parent.IDictEnum;
import com.pay.siip.exceptions.CommonStatusDict;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author : czh
 * description :
 * date : 2021-04-30
 * email 916419307@qq.com
 */
@Data
@XmlTransient
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
@ApiModel(value = "系统 响应实体外壳 数据实体")
public final class BaseResponse<T> implements IBaseEntity {

    private Integer code;

    private String msg;

    private T data;

    private BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构建 成功 响应体
     *
     * @param <T> 数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp() {
        return newSuccessResp(null);
    }

    /**
     * 构建 成功 响应体
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp(T data) {
        return newSuccessResp(CommonStatusDict.SUCCESS.value, data);
    }

    /**
     * 构建 成功 响应体
     *
     * @param msg  响应文本
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应体
     */
    public static <T> BaseResponse<T> newSuccessResp(String msg, T data) {
        return new BaseResponse<>(CommonStatusDict.SUCCESS.key, msg, data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp() {
        return newFailResp(CommonStatusDict.FAIL);
    }

    /**
     * 构建 失败 响应体
     *
     * @param dictEnum 异常枚举
     * @param <T>      数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(IDictEnum<Integer, String> dictEnum) {
        return newFailResp(dictEnum, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param dictEnum 异常枚举
     * @param data     异常数据
     * @param <T>      数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(IDictEnum<Integer, String> dictEnum, T data) {
        return newFailResp(dictEnum.getKey(), dictEnum.getValue(), data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param e   捕获的异常
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(Exception e) {
        return newFailResp(CommonStatusDict.FAIL.key, e.getMessage());
    }

    /**
     * 构建 失败 响应体
     *
     * @param msg 异常文本
     * @param <T> 数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(String msg) {
        return newFailResp(msg, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param msg  异常文本
     * @param data 异常数据
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(String msg, T data) {
        return newFailResp(CommonStatusDict.FAIL.key, msg, data);
    }

    /**
     * 构建 失败 响应体
     *
     * @param code 异常编码
     * @param msg  异常文本
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(Integer code, String msg) {
        return newFailResp(code, msg, null);
    }

    /**
     * 构建 失败 响应体
     *
     * @param code 异常编码
     * @param msg  异常文本
     * @param data 异常数据
     * @param <T>  数据类型
     * @return 失败响应体
     */
    public static <T> BaseResponse<T> newFailResp(Integer code, String msg, T data) {
        return new BaseResponse<>(code, msg, data);
    }
}
