package com.wxx.imooc.adapter.home;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.wxx.imooc.ImoocApplication;
import com.wxx.imooc.R;
import com.wxx.imooc.adapter.base.BaseViewHolder;
import com.wxx.imooc.adapter.base.ItemViewType;
import com.wxx.imooc.bean.HomeEntity;
import com.wxx.imooc.util.Util;

/**
 * 作者: Tangren on 2017-11-09
 * 包名：com.wxx.imooc.adapter.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeTwoItem implements ItemViewType<HomeEntity.DataBean.ListBean> {
    @Override
    public int getItemViewLayoutID() {
        return R.layout.view_home_one_item;
    }

    @Override
    public boolean isForViewType(HomeEntity.DataBean.ListBean item, int position) {
        Log.d("HomeTwoItem",
                "isForViewType(HomeTwoItem.java:31)type==" + item.getType());
        return position == 2;
    }

    @Override
    public void convert(BaseViewHolder holder, HomeEntity.DataBean.ListBean listBean, int position) {
        ImageView logo = holder.getView(R.id.item_logo_view);
        Picasso.with(ImoocApplication.getInstance()).load(listBean.getLogo()).into(logo);
        holder.setText(R.id.item_title_view, listBean.getTitle());
        holder.setText(R.id.item_info_view, listBean.getInfo());
        holder.setText(R.id.item_footer_view, listBean.getText());
        holder.setText(R.id.item_price_view, listBean.getPrice());
        holder.setText(R.id.item_from_view, listBean.getFrom());
        holder.setText(R.id.item_zan_view, listBean.getZan());
//        LinearLayout linearLayout = holder.getView(R.id.product_photo_layout);
//        for (String url : listBean.getUrl()) {
//            linearLayout.addView(crateView(url));
//        }
    }

    private View crateView(String url) {
        ImageView view = new ImageView(ImoocApplication.getInstance());
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(Util.dp2px(ImoocApplication.getInstance(), 100),
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = Util.dp2px(ImoocApplication.getInstance(), 5);
        view.setLayoutParams(params);
        Picasso.with(ImoocApplication.getInstance()).load(url).into(view);
        return view;
    }
}
