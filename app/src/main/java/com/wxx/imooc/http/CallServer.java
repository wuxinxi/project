package com.wxx.imooc.http;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;


public class CallServer {

    private static CallServer httpclient;

    /**
     * 请求队列
     */
    private RequestQueue requestQueue;

    /**
     * 下载队列
     */
    private DownloadQueue downloadQueue;

    private CallServer() {
        requestQueue = NoHttp.newRequestQueue();
        downloadQueue = NoHttp.newDownloadQueue();
    }

    public synchronized static CallServer getHttpclient() {
        if (httpclient == null) {
            httpclient = new CallServer();
        }
        return httpclient;
    }

    /**
     * 添加一个请求到队列
     *
     * @param what     请求标示.
     * @param request  请求对象.
     * @param callback 回调.
     * @param <T>      .
     */
    public <T> void add(int what, Request<T> request, HttpListener<T> callback) {
        requestQueue.add(what, request, new HttpResponseListener<T>
                (callback));
    }

    /**
     * 取消队列中所有请求
     */
    public void cancelAll() {
        requestQueue.cancelAll();
    }


    /**
     * 退出App时停止所有请求
     */
    public void stopAll() {
        requestQueue.stop();
    }

    public void calcelDown() {
        downloadQueue.cancelAll();
    }


}
