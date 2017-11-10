package com.wxx.imooc.http;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.util.List;

/**
 * 作者: Tangren on 2017-10-29
 * 包名：com.wxx.mvp.http
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class ArrayRequest<T> extends RestRequest<List<T>> {

    private Class<T> tClass;

    public ArrayRequest(String url, Class<T> tClass) {
        this(url, RequestMethod.POST, tClass);
        this.tClass = tClass;
    }

    public ArrayRequest(String url, RequestMethod requestMethod, Class<T> tClass) {
        super(url, requestMethod);
        this.tClass = tClass;
        setAccept(Headers.HEAD_VALUE_CONTENT_TYPE_JSON);
    }


    @Override
    public List<T> parseResponse(Headers responseHeaders, byte[] responseBody) throws Exception {
        String result = StringRequest.parseResponseString(responseHeaders, responseBody);
        return JSON.parseArray(result, tClass);
    }
}
