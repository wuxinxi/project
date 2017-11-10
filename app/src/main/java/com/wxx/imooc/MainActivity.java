package com.wxx.imooc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxx.imooc.adapter.base.MultiItemTypeAdapter;
import com.wxx.imooc.adapter.home.HomeElseItem;
import com.wxx.imooc.adapter.home.HomeHeaderItme;
import com.wxx.imooc.adapter.home.HomeOneItem;
import com.wxx.imooc.adapter.home.HomeTwoItem;
import com.wxx.imooc.bean.HomeEntity;
import com.wxx.imooc.http.CallServer;
import com.wxx.imooc.http.HttpListener;
import com.wxx.imooc.http.JsonRequest;
import com.yanzhenjie.nohttp.rest.Response;


/**
 * @author Tangren
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    MultiItemTypeAdapter<HomeEntity.DataBean.ListBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultiItemTypeAdapter<HomeEntity.DataBean.ListBean>(MainActivity.this);
        String url = "http://112.74.102.125/request/request?type=0";
        JsonRequest request = new JsonRequest(url);
        CallServer.getHttpclient().add(0, request, new HttpListener<JSONObject>() {
            @Override
            public void success(int what, Response<JSONObject> response) {
                Log.d("MainActivity",
                        "success(MainActivity.java:47)SUCCESS");
                HomeEntity entity = JSONObject.toJavaObject(JSON.parseObject(response.get().toString()), HomeEntity.class);
                HomeEntity.DataBean.HeadBean head = entity.getData().getHead();

                mAdapter.setNewDatas(entity.getData().getList());
                mAdapter.addItemViewDelegate(new HomeTwoItem());
                mAdapter.addItemViewDelegate(new HomeOneItem());
                mAdapter.addItemViewDelegate(new HomeElseItem());
                mAdapter.addItemViewDelegate(new HomeHeaderItme(MainActivity.this, head));
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void fail(int what, String e) {
                Log.d("MainActivity",
                        "fail(MainActivity.java:60)" + e);
            }
        });
    }
}
