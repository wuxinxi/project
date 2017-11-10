package com.wxx.imooc.adapter.home;

import android.support.v4.view.ViewPager;

import com.wxx.imooc.R;
import com.wxx.imooc.adapter.base.BaseViewHolder;
import com.wxx.imooc.adapter.base.ItemViewType;
import com.wxx.imooc.bean.HomeEntity;

/**
 * 作者: Tangren on 2017-11-10
 * 包名：com.wxx.imooc.adapter.home
 * 邮箱：996489865@qq.com
 * TODO:type=3
 */

public class HomeTypeThreeItem implements ItemViewType<HomeEntity.DataBean.ListBean> {
    @Override
    public int getItemViewLayoutID() {
        return R.layout.view_home_viewpage;
    }

    @Override
    public boolean isForViewType(HomeEntity.DataBean.ListBean item, int position) {
        return item.getType() == 3;
    }

    @Override
    public void convert(BaseViewHolder holder, HomeEntity.DataBean.ListBean listBean, int position) {
        ViewPager viewPager = holder.getView(R.id.page);
        //int type, String logo, String title, String info, String price, String text, String site, String from, String zan, ArrayList<String> url

//        viewPager.setAdapter(new HotSalePagerAdapter(ImoocApplication.getInstance(), Util.handleData()));
    }
}
