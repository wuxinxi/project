package com.wxx.imooc.http;

import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.RestRequest;

import java.util.Map;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.http
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class TRequest {

    public static Builder newBuilder() {
        return new Builder();
    }

    private String url;
    private int what;
    private Map<String, Object> map;
    private RequestMethod method;
    private CacheMode cacheMode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public CacheMode getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }

    private TRequest(Builder builder) {
        this.url = builder.url;
        this.what = builder.what;
        this.map = builder.map;
        this.cacheMode = builder.cacheMode;
        this.method = builder.method;
    }

    public static class Builder<T> {
        private String url;
        private int what = 0;
        private Map<String, Object> map;
        private RequestMethod method = RequestMethod.GET;
        private CacheMode cacheMode = CacheMode.ONLY_REQUEST_NETWORK;


        /**
         * 设置URL
         *
         * @param url .
         * @return .
         */
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * 设置What
         *
         * @param what .
         * @return .
         */
        public Builder setWhat(int what) {
            this.what = what;
            return this;
        }

        /**
         * 设置参数,这里仅支持map
         *
         * @param map .
         * @return .
         */
        public Builder setMap(Map<String, Object> map) {
            this.map = map;
            return this;
        }

        /**
         * 设置请求方式:POST,GET,默认GET
         *
         * @param method .
         * @return .
         */
        public Builder setMethod(RequestMethod method) {
            this.method = method;
            return this;
        }

        /**
         * 设置请求模式详情见CacheMode
         *
         * @param cacheMode .
         * @return .
         */
        public Builder setMode(CacheMode cacheMode) {
            this.cacheMode = cacheMode;
            return this;
        }

        /**
         * 设置回调,仅支持fastJson
         *
         * @param callback .
         * @return .
         */
        public Builder setRequest(HttpListener callback) {
            JsonRequest request = new JsonRequest(url, method);
            request.add(map);
            request.setCacheMode(cacheMode);
            CallServer.getHttpclient().add(what, request, (HttpListener<JSONObject>) callback);
            return this;
        }

        /**
         * 设置回调
         *
         * @param request  request可自定义,继承即可.
         * @param callback .
         * @return .
         */
        public Builder setRequest(RestRequest request, HttpListener callback) {
            CallServer.getHttpclient().add(what, request, (HttpListener<JSONObject>) callback);
            return this;
        }

        public TRequest build() {
            return new TRequest(this);
        }
    }


}
