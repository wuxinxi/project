package com.wxx.imooc;

import android.app.Application;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

/**
 * 作者: Tangren on 2017-11-05
 * 包名：com.wxx.imooc
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class ImoocApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化基本数据
        instance = this;
        NoHttp.initialize(InitializationConfig.newBuilder(this)
                .networkExecutor(new OkHttpNetworkExecutor())
                .build());
        Logger.setDebug(true);
        Logger.setTag("run");
    }

    private volatile static ImoocApplication instance = null;

    public static ImoocApplication getInstance() {
        if (instance == null) {
            synchronized (ImoocApplication.class) {
                if (instance == null) {
                    instance = new ImoocApplication();
                }
            }
        }
        return instance;
    }

}
