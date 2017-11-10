package com.wxx.imooc.life;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.life
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class LifefulHelper {

    public static <T> boolean destroy(LifefulGenerator<T> lifefulGenerator) {
        if (lifefulGenerator == null) {
            return true;
        }

        if (lifefulGenerator.getCallback() == null) {
            return true;
        }

        if (lifefulGenerator.getLifefulWeakReference() == null) {
            return true;
        }

        LifeFul lifeful = lifefulGenerator.getLifefulWeakReference().get();

        return lifeful == null && !lifefulGenerator.isLifefulNull() || lifeful != null && !lifeful.isAlive();

    }
}
