package com.wxx.commonsdk.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * 作者: Tangren on 2017-11-06
 * 包名：com.wxx.commonsdk.okhttp
 * 邮箱：996489865@qq.com
 * TODO:请求工具类
 */

public class CommonOkhttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mClient;

    static {
        OkHttpClient.Builder okHttpClientBuild = new OkHttpClient.Builder();
        okHttpClientBuild.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        okHttpClientBuild.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuild.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuild.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuild.followRedirects(true);
    }
}
