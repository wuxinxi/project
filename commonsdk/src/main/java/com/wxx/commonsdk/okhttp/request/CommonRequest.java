package com.wxx.commonsdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

/**
 * 作者: Tangren on 2017-11-06
 * 包名：com.wxx.commonsdk.okhttp.request
 * 邮箱：996489865@qq.com
 * TODO:封装请求
 */

public class CommonRequest {


    /**
     * 创建一Post请求
     *
     * @param url    URL地址
     * @param params 参数
     * @return 返回一个POST 请求对象
     */
    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url, params, null);
    }


    /**
     * 创建一Post请求
     *
     * @param url     URL地址
     * @param params  参数
     * @param headers 请求头
     * @return 返回一个POST 请求对象
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers) {
        FormBody.Builder mFromBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry :
                    params.urlParams.entrySet()) {
                mFromBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }

        //添加请求头
        Headers.Builder mHeadersBuild = new Headers.Builder();
        for (Map.Entry<String, String> entry :
                headers.urlParams.entrySet()) {
            mHeadersBuild.add(entry.getKey(), entry.getValue());
        }
        Headers mHeaders = mHeadersBuild.build();
        FormBody formBody = mFromBodyBuild.build();
        return new Request
                .Builder()
                .url(url)
                .post(formBody)
                .headers(mHeaders)
                .build();
    }


    /**
     * 创建一个Get请求
     *
     * @param url    URL地址
     * @param params 参数
     * @return 返回一个GET请求对象
     */
    public static Request createGetRequest(String url, RequestParams params) {
        return createGetRequest(url, params, null);
    }

    /**
     * 创建一个GET请求
     *
     * @param url     URL地址
     * @param params  参数
     * @param headers 请求头
     * @return 返回同一个GET请求对象
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {

        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry :
                    params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        Headers.Builder mHeadersBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry :
                    headers.urlParams.entrySet()) {
                mHeadersBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeaders = mHeadersBuild.build();
        return new Request
                .Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeaders)
                .build();
    }
}
