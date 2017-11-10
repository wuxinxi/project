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
import com.wxx.imooc.util.PicassoImageLoad;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.widget
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HeaderLayout extends LinearLayout {

    private HomeEntity.DataBean.HeadBean mHeadBean;
    private Banner mBanner;
    private TextView tip;
    private ImageView[] img = new ImageView[4];
    private Context mContext;
    private LinearLayout contentLayout;

    public HeaderLayout(Context context, HomeEntity.DataBean.HeadBean headBean) {
        this(context, null, headBean);
    }

    public HeaderLayout(Context context, AttributeSet attrs, HomeEntity.DataBean.HeadBean headBean) {
        this(context, attrs, 0, headBean);
    }

    public HeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, HomeEntity.DataBean.HeadBean headBean) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mHeadBean = headBean;
        initView();
    }

    public void setData(HomeEntity.DataBean.HeadBean headBean) {
        mHeadBean = headBean;
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_home_header, this);
        mBanner = (Banner) view.findViewById(R.id.banner);
        contentLayout = (LinearLayout) view.findViewById(R.id.content_layout);
        tip = (TextView) view.findViewById(R.id.tip);
        img[0] = (ImageView) view.findViewById(R.id.head_image_one);
        img[1] = (ImageView) view.findViewById(R.id.head_image_two);
        img[2] = (ImageView) view.findViewById(R.id.head_image_three);
        img[3] = (ImageView) view.findViewById(R.id.head_image_four);

        initBanner();

        tip.setText("------>>今日最新<<------");

        for (int i = 0; i < img.length; i++) {
            Picasso.with(ImoocApplication.getInstance()).load(mHeadBean.getMiddle().get(i)).into(img[i]);
        }

        initfooter();

    }

    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setImages(mHeadBean.getAds());
        mBanner.setDelayTime(1200);
        mBanner.setImageLoader(new PicassoImageLoad());
        mBanner.start();
    }

    private void initfooter() {
        for (HomeEntity.DataBean.HeadBean.FooterBean footerBean : mHeadBean.getFooter()) {
            contentLayout.addView(createFootView(footerBean));
        }
    }

    private View createFootView(HomeEntity.DataBean.HeadBean.FooterBean footerBean) {
        return new HomeFootLayout(mContext, footerBean);
    }

}
