package com.wxx.imooc.http;


import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;


public class HttpResponseListener<T> implements OnResponseListener<T> {

    private HttpListener<T> httpListener;
    private static final int int400 = 400;
    private static final int int405 = 405;

    /**
     * @param callback 回调对象
     */
    HttpResponseListener(HttpListener<T> callback) {
        this.httpListener = callback;
    }

    @Override
    public void onFinish(int arg0) {
        Logger.d("完成");

    }

    @Override
    public void onStart(int arg0) {
        Logger.i("开始");
    }

    // 成功回调
    @Override
    public void onSucceed(int what, Response<T> response) {
        int responseCode = response.getHeaders().getResponseCode();
        System.out.println("responseCode:" + responseCode + "");
        if (responseCode > int400) {
            if (responseCode == int405) {
                Logger.d("服务器暂不支持该类型！");
                httpListener.fail(what, "服务器暂不支持该类型");
            }
        }

        if (httpListener != null) {
            httpListener.success(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        Logger.i("失败");
        if (httpListener != null) {
            httpListener.fail(what, response.getException().getMessage());
        }

    }


}
