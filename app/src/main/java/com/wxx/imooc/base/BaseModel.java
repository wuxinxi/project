package com.wxx.imooc.base;

import com.wxx.imooc.life.OnLoadLifefulListener;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * 作者: Tangren on 2017-10-28
 * 包名：com.wxx.mvp.base
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public interface BaseModel<T> {

    /**
     * 请求
     *
     * @param what     标示.
     * @param clazz    实体类
     * @param request  请求request.
     * @param listener 绑定生命周期的回调.
     */
    void load(int what, Class<T> clazz, Request request, OnLoadLifefulListener<T> listener);

}
