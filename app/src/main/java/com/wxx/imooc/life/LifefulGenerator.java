package com.wxx.imooc.life;

import java.lang.ref.WeakReference;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.life
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public interface LifefulGenerator<CallBack> {

    /**
     * @return the callback.
     */
    CallBack getCallback();

    /**
     * @return the weak reference which bind with life cycle.
     */
    WeakReference<LifeFul> getLifefulWeakReference();

    /**
     * Check to see whether the lifeful is null.
     *
     * @return true if lifeful is null
     */
    boolean isLifefulNull();
}
