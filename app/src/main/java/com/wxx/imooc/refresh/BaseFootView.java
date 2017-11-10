package com.wxx.imooc.refresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 作者: Tangren on 2017-11-02
 * 包名：com.wxx.view.refresh
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public abstract class BaseFootView extends FrameLayout implements OnFootListener {


    public BaseFootView(@NonNull Context context) {
        super(context);
    }

    public BaseFootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFootView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @param refreshView .
     */
    public abstract void setRefreshView(TRefreshView refreshView);
}
