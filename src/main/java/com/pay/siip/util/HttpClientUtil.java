package com.pay.siip.util;

import com.pay.siip.constant.ParamConstant;
import com.pay.siip.exceptions.CommonException;
import com.pay.siip.validate.EmptyAssert;
import com.pay.siip.validate.EmptyValidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


/**
 * @author : czh
 * description :
 * date : 2021-05-23
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public class HttpClientUtil {

    /**
     * 调用HTTP-GET
     */
    public static String doGet(String url, Map<String, String> headMap) {
        HttpGet httpGet = getHttpGet(url);
        setHeaderConfig(httpGet, url, headMap);
        return invokeToResp(HttpPoolUtil.getHttpClient(0), httpGet);
    }

    /**
     * 调用HTTP-POST
     */
    public static String doPost(String url, String reqJson, Map<String, String> headMap) {
        System.out.println(reqJson);
        HttpPost httpPost = getHttpPost(url);
        for (Map.Entry<String, String> entry : headMap.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        // 防止被当成攻击添加的
        httpPost.setHeader("User-Agent", HttpURLConnection.userAgent);
        // 接收参数设置
        httpPost.setHeader("Accept", "application/json");
        // 字符串编码
        StringEntity entity = new StringEntity(reqJson, Consts.UTF_8);
        // 设置content-type
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return invokeToResp(HttpPoolUtil.getHttpClient(0), httpPost);
    }

    /**
     * 获取post请求实体
     */
    private static HttpPost getHttpPost(String url) {
        EmptyAssert.isNotBlank(url);
        HttpPost httpPost = new HttpPost(url);
        makeRequestConfig(httpPost);
        return httpPost;
    }

    /**
     * 获取get请求实体
     */
    private static HttpGet getHttpGet(String url) {
        EmptyAssert.isNotBlank(url);
        HttpGet httpGet = new HttpGet(url);
        makeRequestConfig(httpGet);
        return httpGet;
    }

    /**
     * 请求体，设置 Config
     */
    private static void makeRequestConfig(HttpRequestBase request) {
        EmptyAssert.isNotNull(request);
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(ParamConstant.getSocketTimeout())
                .setConnectTimeout(ParamConstant.getConnectTimeout())
                .build();
        request.setConfig(config);
    }

    /**
     * 配置请求头参数
     */
    private static void setHeaderConfig(HttpRequestBase request, String url, Map<String, String> headMap) {
        request.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;CharSet=gbk"));
        request.setHeader(new BasicHeader("UserAgent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36"));
        request.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36"));
        request.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        request.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        request.setHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
        request.setHeader(new BasicHeader("Connection", "keep-alive"));
        if (EmptyValidate.isNotBlank(url)) {
            request.setHeader(new BasicHeader("Host", getHost(url)));
            request.setHeader(new BasicHeader("Origin", "https://" + getHost(url)));
        }

        if (EmptyValidate.isEmpty(headMap)) {
            return;
        }
        for (Map.Entry<String, String> entry : headMap.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 获取请求IP或域名
     */
    private static String getHost(String url) {
        if (EmptyValidate.isBlank(url) || (!(url.startsWith("http://") && !url.startsWith("https://")))) {
            return StringUtils.EMPTY;
        }

        url = url.substring(url.indexOf("://") + 3);
        if (url.contains("/")) {
            url = url.substring(0, url.indexOf("/"));
        }
        return url;
    }

    protected static String invokeToResp(HttpRequestBase request) {
        return invokeToResp(HttpPoolUtil.getHttpClient(), request);
    }

    /**
     * 发起http请求
     */
    protected static String invokeToResp(CloseableHttpClient client, HttpRequestBase request) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CommonException("发送请求失败");
        } finally {
            StreamUtil.close(response);
        }
    }

    protected static String invokeToCookies(HttpRequestBase request) {
        return invokeToCookies(HttpPoolUtil.getHttpClient(), request);
    }

    /**
     * 发起http请求
     */
    protected static String invokeToCookies(CloseableHttpClient client, HttpRequestBase request) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);

            Header[] cookies = response.getHeaders("set-cookie");
            if (EmptyValidate.isEmpty(cookies)) {
                cookies = response.getHeaders("SET-COOKIE");
                if (EmptyValidate.isEmpty(cookies)) {
                    return StringUtils.EMPTY;
                }
            }

            StringBuilder cookiesBuilder = new StringBuilder();
            for (Header cookie : cookies) {
                HeaderElement headerElement = cookie.getElements()[0];
                cookiesBuilder.append(headerElement.getName())
                        .append("=")
                        .append(headerElement.getValue())
                        .append("; ");
            }
            return cookiesBuilder.toString();
        } catch (IOException e) {
            throw new CommonException("发送请求失败");
        } finally {
            StreamUtil.close(response);
        }
    }
}
