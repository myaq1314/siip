package com.pay.siip.util;

import com.pay.siip.constant.DateConstant;
import com.pay.siip.exceptions.CommonException;
import com.pay.siip.validate.EmptyAssert;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-24
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public final class DateUtil {

    public static DateFormat getFormatter() {
        return getFormatter(DateConstant.DATETIME_STANDARD());
    }

    public static DateFormat getFormatter(String pattern) {
        EmptyAssert.isNotBlank(pattern, "日期格式文本无效");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            EmptyAssert.isNotNull(formatter);
            return formatter;
        } catch (IllegalArgumentException e) {
            throw new CommonException("日期格式文本无效");
        }
    }

    public static String convertNowToText() {
        return convertNowToText(getFormatter());
    }

    public static String convertNowToText(String pattern) {
        return convertNowToText(getFormatter(pattern));
    }

    public static String convertNowToText(DateFormat formatter) {
        return convertToText(new Date(), formatter);
    }


    public static String convertToText(Date date) {
        return convertToText(date, getFormatter());
    }

    public static String convertToText(Date date, String pattern) {
        return convertToText(date, getFormatter(pattern));
    }

    public static String convertToText(Date date, DateFormat formatter) {
        EmptyAssert.allNotNull(date, formatter);

        return formatter.format(date);
    }

    public static Date convertToDate(String dateText) {
        return convertToDate(dateText, getFormatter());
    }

    public static Date convertToDate(String dateText, String pattern) {
        return convertToDate(dateText, getFormatter(pattern));
    }

    public static Date convertToDate(String dateText, DateFormat formatter) {
        EmptyAssert.isNotBlank(dateText);
        EmptyAssert.isNotNull(formatter);

        try {
            return formatter.parse(dateText);
        } catch (ParseException e) {
            throw new CommonException("不匹配的日期时间文本 和 日期时间格式");
        }
    }
}
