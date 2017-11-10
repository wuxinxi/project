package com.wxx.imooc.life;

import java.lang.ref.WeakReference;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.life
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class DefaultLifefulGenerator<CallBack> implements LifefulGenerator<CallBack> {
    private WeakReference<LifeFul> weakReference;
    private boolean isLifefulNull;
    private CallBack callback;

    DefaultLifefulGenerator(CallBack callback, LifeFul lifeful) {
        this.callback = callback;
        weakReference = new WeakReference<>(lifeful);
        isLifefulNull = lifeful == null;
    }

    /**
     * @return callback
     */
    @Override
    public CallBack getCallback() {
        return callback;
    }

    /**
     * @return weak reference
     */
    @Override
    public WeakReference<LifeFul> getLifefulWeakReference() {
        return weakReference;
    }

    /**
     * Returns true if the lifeful is null.
     *
     * @return true if str is null, else false
     */
    @Override
    public boolean isLifefulNull() {
        return isLifefulNull;
    }
}
