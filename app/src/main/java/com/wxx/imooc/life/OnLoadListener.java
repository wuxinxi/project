package com.wxx.imooc.life;

import java.util.List;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.life
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public interface OnLoadListener<T> {

    /**
     * 成功的回调
     *
     * @param what .
     * @param data .
     */
    void onLoadSuccess(int what, T data);

    /**
     * 请求列表成功的回调
     *
     * @param what     .
     * @param listData .
     */
    void onLoadListSuccess(int what, List<T> listData);

    /**
     * 失败的回调
     *
     * @param what .
     * @param var  .
     */
    void onLoadFail(int what, String var);
}
