package com.pay.siip.api;

import com.pay.siip.entity.BaseResponse;
import com.pay.siip.exceptions.CommonException;
import com.pay.siip.exceptions.CommonStatusDict;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-05-22
 * email 916419307@qq.com
 */
@Slf4j
@ControllerAdvice
@SuppressWarnings("rawtypes")
public class BaseControllerAdvice {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public BaseResponse handleCommonException(CommonException commonException) {
        log.error(commonException.getMessage(), commonException);
        return BaseResponse.newFailResp(commonException.getCode(), commonException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse handleRuntimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage(), runtimeException);
        return BaseResponse.newFailResp(CommonStatusDict.SYSTEM_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleSpringValidateException(MethodArgumentNotValidException validException) {
        log.error(validException.getMessage(), validException);
        return BaseResponse.newFailResp(
                CommonStatusDict.BAD_PARAM.key,
                parseBindingResult(validException.getBindingResult())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public BaseResponse handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.error(illegalArgumentException.getMessage(), illegalArgumentException);
        return BaseResponse.newFailResp(CommonStatusDict.BAD_PARAM);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public BaseResponse handleNullPointerException(NullPointerException nullPointerException) {
        log.error(nullPointerException.getMessage(), nullPointerException);
        return BaseResponse.newFailResp(CommonStatusDict.BAD_PARAM);
    }

    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public BaseResponse handleServletException(ServletException servletException) {
        log.error(servletException.getMessage(), servletException);
        return BaseResponse.newFailResp(CommonStatusDict.BAD_PARAM);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.newFailResp(CommonStatusDict.SYSTEM_EXCEPTION);
    }

    /**
     * Get bind and validation errors in binding result.
     *
     * @param bindingResult spring validation binding result
     * @return error messages in string
     */
    private String parseBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errorMessages.add(objectError.getDefaultMessage());
            }
            return StringUtils.join(errorMessages, "&");
        }
        return "";
    }
}
