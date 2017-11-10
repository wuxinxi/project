package com.wxx.imooc.base;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxx.imooc.http.ArrayRequest;
import com.wxx.imooc.http.CallServer;
import com.wxx.imooc.http.HttpListener;
import com.wxx.imooc.http.JsonRequest;
import com.wxx.imooc.life.OnLoadLifefulListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * 作者: Tangren on 2017-10-28
 * 包名：com.wxx.mvp.base
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class BaseModelCompl<T> implements BaseModel<T> {


    @Override
    public void load(int what, Class<T> clazz, Request request, OnLoadLifefulListener<T> listener) {
        request(what, clazz, request, listener);
    }

    private void request(int what, final Class<T> clazz, final Request request, final OnLoadLifefulListener<T> listener) {
        if (request == null) {
            return;
        }
        CallServer.getHttpclient().add(what, request, new HttpListener() {
            @Override
            public void success(int what, Response response) {
                Log.d("BaseModelCompl",
                        "success(BaseModelCompl.java:40)" + response.get().toString());
                if (listener != null) {
                    if (response.get() != null) {
                        if (request instanceof JsonRequest) {
                            listener.onLoadSuccess(what, JSONObject.toJavaObject(JSON.parseObject(response.get().toString()), clazz));
                        } else if (request instanceof ArrayRequest) {
                            listener.onLoadListSuccess(what, (List<T>) response.get());
                        }
                    } else {
                        listener.onLoadFail(what, "Unknown Error");
                    }

                }
            }

            @Override
            public void fail(int what, String e) {
                if (listener != null) {
                    listener.onLoadFail(what, e);
                }
            }
        });
    }

}
