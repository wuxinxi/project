package com.wxx.imooc.fragement.home;

import android.util.Log;

import com.wxx.imooc.base.BaseModel;
import com.wxx.imooc.base.BaseModelCompl;
import com.wxx.imooc.base.BasePresenter;
import com.wxx.imooc.base.BaseView;
import com.wxx.imooc.bean.HomeEntity;
import com.wxx.imooc.http.JsonRequest;
import com.wxx.imooc.life.LifeFul;
import com.wxx.imooc.life.OnLoadLifefulListener;
import com.wxx.imooc.life.OnLoadListener;

import java.util.List;

/**
 * 作者: Tangren on 2017-11-07
 * 包名：com.wxx.imooc.fragement.home
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomePresenter extends BasePresenter<BaseView<HomeEntity>> {

    private BaseView<HomeEntity> view;
    private BaseModel<HomeEntity> model;

    HomePresenter(BaseView<HomeEntity> view) {
        this.view = view;
        model = new BaseModelCompl<>();
    }

    void requestHome(int what, JsonRequest request, LifeFul lifeFul) {
        model.load(what, HomeEntity.class, request, new OnLoadLifefulListener<HomeEntity>(new OnLoadListener<HomeEntity>() {
            @Override
            public void onLoadSuccess(int what, HomeEntity data) {

                    view.onSuccess(what, data);

            }

            @Override
            public void onLoadListSuccess(int what, List<HomeEntity> listData) {
                Log.d("HomePresenter",
                    "onLoadListSuccess(HomePresenter.java:48)");
            }


            @Override
            public void onLoadFail(int what, String var) {
                Log.d("HomePresenter",
                    "onLoadFail(HomePresenter.java:55)"+var);
            }
        }, lifeFul));
    }
}
