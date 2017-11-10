package com.wxx.imooc.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wxx.imooc.ImoocApplication;
import com.wxx.imooc.R;
import com.wxx.imooc.bean.HomeCommonEntity;

import java.util.List;

/**
 * 作者: Tangren on 2017-11-08
 * 包名：com.wxx.imooc.adapter
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HotSalePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomeCommonEntity> mData;
    private LayoutInflater mInflater;

    public HotSalePagerAdapter(Context mContext, List<HomeCommonEntity> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public HotSalePagerAdapter(List<HomeCommonEntity> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(ImoocApplication.getInstance());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        HomeCommonEntity entity = mData.get(position % mData.size());
        View rootView = mInflater.inflate(R.layout.view_page_item, null);
        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        TextView infoView = (TextView) rootView.findViewById(R.id.info);
        TextView gonggaoView = (TextView) rootView.findViewById(R.id.gonggao_view);
        TextView saleView = (TextView) rootView.findViewById(R.id.sale_num_view);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = (ImageView) rootView.findViewById(R.id.image_one);
        imageViews[1] = (ImageView) rootView.findViewById(R.id.image_two);
        imageViews[2] = (ImageView) rootView.findViewById(R.id.image_three);

        titleView.setText(entity.title);
        infoView.setText(entity.price);
        gonggaoView.setText(entity.info);
        saleView.setText(entity.text);
        for (int i = 0; i < imageViews.length; i++) {
            Picasso.with(ImoocApplication.getInstance()).load(entity.url.get(i)).into(imageViews[i]);
        }
        container.addView(rootView);
        return rootView;
    }
}
