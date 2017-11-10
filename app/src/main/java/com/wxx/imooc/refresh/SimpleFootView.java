package com.wxx.imooc.refresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxx.imooc.R;
import com.wxx.imooc.widget.LoadingView;


/**
 * 作者: Tangren on 2017-11-02
 * 包名：com.wxx.view.refresh
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class SimpleFootView extends BaseFootView {

    private View footView;
    private TextView tip;
    private LoadingView loadingView;
    private TRefreshView refreshView;

    public SimpleFootView(@NonNull Context context) {
        this(context, null);
    }

    public SimpleFootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleFootView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        footView = LayoutInflater.from(context).inflate(R.layout.view_simple, this);
        tip = (TextView) footView.findViewById(R.id.tip);
        loadingView = (LoadingView) footView.findViewById(R.id.progress);
    }

    @Override
    public void onLoadMore() {
        loadingView.show();
        tip.setVisibility(View.GONE);
        footView.setOnClickListener(null);
    }

    @Override
    public void onHideLoad() {
        loadingView.hide();
        tip.setVisibility(View.GONE);
    }

    @Override
    public void onNoMore() {
        setStatus();
        tip.setText("--已无更多数据--");
        footView.setOnClickListener(null);
    }

    @Override
    public void onError() {
        setStatus();
        tip.setText("--网络出了小差,点我重试--");
        footView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载,更新UI
                onLoadMore();
                refreshView.listener.onLoadMore();

            }
        });
    }

    private void setStatus() {
        loadingView.hide();
        tip.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRefreshView(TRefreshView refreshView) {
        this.refreshView = refreshView;
    }

}
