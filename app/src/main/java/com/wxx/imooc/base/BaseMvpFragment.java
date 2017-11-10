package com.wxx.imooc.base;

import android.os.Build;

import com.wxx.imooc.life.LifeFul;

/**
 * 作者: Tangren on 2017-11-05
 * 包名：com.wxx.imooc.base
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends BaseFragment implements LifeFul {

    protected T getChildPresenter() {
        return null;
    }

    protected T mPresenter;

    @Override
    protected void initPresenter() {
        if (null != getChildPresenter()) {
            mPresenter = getChildPresenter();
        }
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public boolean isAlive() {
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            return !(activity.isDestroyed() || activity.isFinishing());
        }

        return !activity.isFinishing();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

}
