package com.wxx.imooc.base;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.base
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public interface BaseView<T> {

    /**
     * 成功的回调方法
     *
     * @param what .
     * @param data .
     */
    void onSuccess(int what, T data);

    /**
     * 失败的回调
     *
     * @param what 标示
     * @param var  失败原因
     */
    void onFail(int what, String var);

}
