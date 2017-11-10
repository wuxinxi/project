package com.wxx.imooc.fragement.home;

import android.os.Bundle;

import com.wxx.imooc.R;
import com.wxx.imooc.adapter.base.MultiItemTypeAdapter;
import com.wxx.imooc.base.BaseMvpFragment;
import com.wxx.imooc.base.BaseView;
import com.wxx.imooc.bean.FootEntity;
import com.wxx.imooc.bean.HeaderEntity;
import com.wxx.imooc.bean.HomeCommonEntity;
import com.wxx.imooc.bean.HomeEntity;
import com.wxx.imooc.http.JsonRequest;
import com.wxx.imooc.refresh.TRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者: Tangren on 2017-11-05
 * 包名：com.wxx.imooc.fragement.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeFragment extends BaseMvpFragment<BaseView<HomeEntity>, HomePresenter> implements TRefreshView.OnLoadListener, BaseView<HomeEntity> {
    @BindView(R.id.t_refresh_view)
    TRefreshView tRefreshView;

    private MultiItemTypeAdapter<Object> mAdapter;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePresenter getChildPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initView() {
        mAdapter = new MultiItemTypeAdapter<Object>(activity);
        tRefreshView.setOnLoadListener(this);
    }

    @Override
    protected void initData() {
        String url = "http://112.74.102.125/request/request?type=0";
//        String url = "http://112.124.22.238:8081/course_api/wares/list?categoryId=2&curPage=1&pageSize=12";
        JsonRequest request = new JsonRequest(url);
        mPresenter.requestHome(0, request, this);
    }

    @Override
    protected int layoutID() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void onRefresh() {
        tRefreshView.complete();
    }

    @Override
    public void onLoadMore() {
        tRefreshView.complete();
        tRefreshView.noNoMore();
    }

    @Override
    public void onSuccess(int what, HomeEntity data) {
        List<Object> list = new ArrayList<>();
        list.add(new HeaderEntity(data.getData().getHead().getAds(), data.getData().getHead().getMiddle()));
        List<HomeEntity.DataBean.HeadBean.FooterBean> footer = data.getData().getHead().getFooter();
        for (int i = 0; i < footer.size(); i++) {
            list.add(new FootEntity(footer.get(i).getTitle()
                    , footer.get(i).getInfo()
                    , footer.get(i).getFrom()
                    , footer.get(i).getImageOne()
                    , footer.get(i).getImageTwo()
                    , footer.get(i).getDestationUrl()));
        }
        List<HomeEntity.DataBean.ListBean> list1 = data.getData().getList();
        ArrayList<String> url = (ArrayList<String>) list1.get(1).getUrl();
        list.add(new HomeCommonEntity(list1.get(1).getType()
                , list1.get(1).getLogo()
                , list1.get(1).getTitle()
                , list1.get(1).getInfo()
                , list1.get(1).getPrice()
                , list1.get(1).getText()
                , list1.get(1).getSite()
                , list1.get(1).getFrom()
                , list1.get(1).getZan()
                , url));
        for (int i = 0; i < footer.size(); i++) {
            list.add(new FootEntity(footer.get(i).getTitle()
                    , footer.get(i).getInfo()
                    , footer.get(i).getFrom()
                    , footer.get(i).getImageOne()
                    , footer.get(i).getImageTwo()
                    , footer.get(i).getDestationUrl()));
        }

    }


    @Override
    public void onFail(int what, String var) {

    }
}
