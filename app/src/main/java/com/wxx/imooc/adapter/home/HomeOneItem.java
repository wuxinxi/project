package com.wxx.imooc.adapter.home;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wxx.imooc.ImoocApplication;
import com.wxx.imooc.R;
import com.wxx.imooc.adapter.base.BaseViewHolder;
import com.wxx.imooc.adapter.base.ItemViewType;
import com.wxx.imooc.bean.HomeEntity;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.adapter.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeOneItem implements ItemViewType<HomeEntity.DataBean.ListBean> {
    @Override
    public int getItemViewLayoutID() {
        return R.layout.view_home_one_item;
    }

    @Override
    public boolean isForViewType(HomeEntity.DataBean.ListBean item, int position) {
        return position==1;
    }

    @Override
    public void convert(BaseViewHolder holder, HomeEntity.DataBean.ListBean listBean, int position) {
        ImageView logo = holder.getView(R.id.item_logo_view);
        holder.setText(R.id.item_title_view, listBean.getTitle());
        holder.setText(R.id.item_info_view, listBean.getInfo());
        holder.setText(R.id.item_footer_view, listBean.getText());
        holder.setText(R.id.item_price_view, listBean.getPrice());
        holder.setText(R.id.item_from_view, listBean.getFrom());
        holder.setText(R.id.item_zan_view, listBean.getZan());
        Picasso.with(ImoocApplication.getInstance()).load(listBean.getUrl().get(0)).into(logo);
    }
}
