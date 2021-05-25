package com.pay.siip.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author : czh
 * description :
 * date : 2021-05-23
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings("unused")
public final class HttpPoolUtil {

    private static final PoolingHttpClientConnectionManager connectionManager;

    static {
        int maxPerRoute = 20;
        int maxTotal = 400;

        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", buildSSLConnectionSocketFactory())  //todo:实现SSL绕过
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(reg);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        connectionManager.setMaxTotal(maxTotal);
    }

    /**
     * SSL操作
     * <p>
     * 实现X509TrustManager，绕过SSL
     */
    private static SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        try {
            return new SSLConnectionSocketFactory(createIgnoreVerifySSL()); // 优先绕过安全证书
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("ssl connection fail", e);
        }
        return SSLConnectionSocketFactory.getSocketFactory();
    }

    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sc = SSLContext.getInstance("SSLv3");
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    public static CloseableHttpClient getHttpClient() {
        return getHttpClient(3);
    }

    public static CloseableHttpClient getHttpClient(int retryCount) {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, false));
        return httpClientBuilder.build();
    }
}
