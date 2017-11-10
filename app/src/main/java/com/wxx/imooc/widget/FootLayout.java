package com.wxx.imooc.widget;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxx.imooc.bean.HomeEntity;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.widget
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class FootLayout extends LinearLayout {


    private Context mContext;
    private TextView title,info;

    public FootLayout(Context context, HomeEntity.DataBean.ListBean listBean ) {
        super(context);

    }
}
