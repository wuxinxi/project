package com.wxx.imooc.refresh;

/**
 * 作者: Tangren on 2017-11-02
 * 包名：com.wxx.view.refresh
 * 邮箱：996489865@qq.com
 * TODO:Foot监听
 */

public interface OnFootListener {
    /**
     * 正常加载的view
     */
    void onLoadMore();

    /**
     * 隐藏footView
     */
    void onHideLoad();

    /**
     * 没有更多的view
     */
    void onNoMore();

    /**
     * 加载错误view
     */
    void onError();
}
