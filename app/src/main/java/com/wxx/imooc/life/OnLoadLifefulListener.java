package com.wxx.imooc.life;

import java.util.List;

/**
 * 作者: Tangren on 2017-10-27
 * 包名：com.wxx.mvp.life
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class OnLoadLifefulListener<T> implements OnLoadListener<T> {

    private LifefulGenerator<OnLoadListener<T>> lifefulGenerator;

    public OnLoadLifefulListener(OnLoadListener<T> listener, LifeFul lifeful) {
        lifefulGenerator = new DefaultLifefulGenerator<>(listener, lifeful);
    }

    @Override
    public void onLoadSuccess(int what, T success) {
        if (LifefulHelper.destroy(lifefulGenerator)) {
            return;
        }
        lifefulGenerator.getCallback().onLoadSuccess(what, success);
    }

    @Override
    public void onLoadListSuccess(int what, List<T> listData) {
        if (LifefulHelper.destroy(lifefulGenerator)) {
            return;
        }
        lifefulGenerator.getCallback().onLoadListSuccess(what, listData);
    }

    @Override
    public void onLoadFail(int what, String error) {
        if (LifefulHelper.destroy(lifefulGenerator)) {
            return;
        }
        lifefulGenerator.getCallback().onLoadFail(what, error);
    }
}
