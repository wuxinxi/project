package com.wxx.imooc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wxx.imooc.ImoocApplication;
import com.wxx.imooc.R;
import com.wxx.imooc.bean.HomeEntity;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.widget
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeFootLayout extends LinearLayout {
    private Context mContext;
    private TextView title, info, from;
    private ImageView img1, img2;

    private HomeEntity.DataBean.HeadBean.FooterBean footerBean;

    public HomeFootLayout(Context context, HomeEntity.DataBean.HeadBean.FooterBean footerBean) {
        this(context, null, footerBean);
    }

    public HomeFootLayout(Context context, AttributeSet attrs, HomeEntity.DataBean.HeadBean.FooterBean footerBean) {
        this(context, attrs, 0, footerBean);
    }

    public HomeFootLayout(Context context, AttributeSet attrs, int defStyleAttr, HomeEntity.DataBean.HeadBean.FooterBean footerBean) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.footerBean = footerBean;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_home_footer, this);
        title = (TextView) view.findViewById(R.id.title);
        info = (TextView) view.findViewById(R.id.info);
        from = (TextView) view.findViewById(R.id.from);
        img1 = (ImageView) view.findViewById(R.id.icon_1);
        img2 = (ImageView) view.findViewById(R.id.icon_2);

        initData();
    }

    private void initData() {
        title.setText(footerBean.getTitle());
        info.setText(footerBean.getInfo());
        from.setText(footerBean.getFrom());
        Picasso.with(ImoocApplication.getInstance()).load(footerBean.getImageOne()).into(img1);
        Picasso.with(ImoocApplication.getInstance()).load(footerBean.getImageTwo()).into(img2);
    }
}
