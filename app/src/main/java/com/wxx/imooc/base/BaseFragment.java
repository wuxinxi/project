package com.wxx.imooc.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: Tangren on 2017-11-04
 * 包名：com.wxx.imooc.base
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public abstract class BaseFragment extends Fragment {


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

    protected View rootView;

    private Unbinder unbinder;

    public Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(layoutID(), null);
            unbinder = ButterKnife.bind(this, rootView);
            activity =getActivity();
            initPresenter();
            initView();
            initData();
        }

        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("BaseFragment",
                "onAttach(BaseFragment.java:79)");
        Log.d("BaseFragment",
                "onAttach(BaseFragment.java:79)");
        Log.d("BaseFragment",
                "onAttach(BaseFragment.java:79)");
        activity = (Activity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
