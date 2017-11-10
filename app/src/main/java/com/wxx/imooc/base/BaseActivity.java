package com.wxx.imooc.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 作者: Tangren on 2017-11-04
 * 包名：com.wxx.imooc.base
 * 邮箱：996489865@qq.com
 * TODO:公共Activity,实现一些公共方法
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 布局ID
     *
     * @return .
     */
    protected abstract int layoutID();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化presenter
     */
    protected void initPresenter() {

    }


    public Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID());
        ButterKnife.bind(this);
        activity = this;
        initPresenter();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
