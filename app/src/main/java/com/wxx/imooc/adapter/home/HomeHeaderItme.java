package com.wxx.imooc.adapter.home;

import android.content.Context;
import android.widget.FrameLayout;

import com.wxx.imooc.R;
import com.wxx.imooc.adapter.base.BaseViewHolder;
import com.wxx.imooc.adapter.base.ItemViewType;
import com.wxx.imooc.bean.HomeEntity;
import com.wxx.imooc.widget.HeaderLayout;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.adapter.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeHeaderItme implements ItemViewType<HomeEntity.DataBean.ListBean> {

    private HomeEntity.DataBean.HeadBean headBean;
    private Context mContext;

    public HomeHeaderItme(Context context, HomeEntity.DataBean.HeadBean headBean) {
        this.headBean = headBean;
        this.mContext = context;
    }

    @Override
    public int getItemViewLayoutID() {
        return R.layout.view_home;
    }

    @Override
    public boolean isForViewType(HomeEntity.DataBean.ListBean item, int position) {
        return position==0;
    }

    @Override
    public void convert(BaseViewHolder holder, HomeEntity.DataBean.ListBean o, int position) {
        FrameLayout layout = holder.getView(R.id.header_layout);
        layout.addView(new HeaderLayout(mContext, headBean));
    }
}
