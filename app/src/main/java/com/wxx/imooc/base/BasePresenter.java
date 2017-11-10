package com.wxx.imooc.base;

import java.lang.ref.WeakReference;

/**
 * 作者: Tangren on 2017-11-05
 * 包名：com.wxx.imooc.base
 * 邮箱：996489865@qq.com
 * TODO:公共Presenter,绑定生命周期
 */

public abstract class BasePresenter<T> {

    private WeakReference<T> mViewRef;

    void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}