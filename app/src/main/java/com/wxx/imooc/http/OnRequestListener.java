package com.wxx.imooc.http;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.http
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public interface OnRequestListener<T> {
    void onSuccess(int what, T data);

    void onFail(int what, String var);
}
