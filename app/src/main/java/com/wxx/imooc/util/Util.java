package com.wxx.imooc.util;

import android.content.Context;

import com.wxx.imooc.bean.HomeCommonEntity;

import java.util.ArrayList;

/**
 * 作者: Tangren on 2017-10-30
 * 包名：com.wxx.view.util
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class Util {

    /**
     * dp 转 px
     *
     * @param context .
     * @param value   .
     * @return .
     */
    public static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }


    public static boolean checkQR(long currentTime, long lastTime) {
        return currentTime - lastTime > 2000;
    }


    //为ViewPager结构化数据
    public static ArrayList<HomeCommonEntity> handleData(HomeCommonEntity value) {
        ArrayList<HomeCommonEntity> values = new ArrayList<>();
        String[] titles = value.title.split("@");
        String[] infos = value.info.split("@");
        String[] prices = value.price.split("@");
        String[] texts = value.text.split("@");
        ArrayList<String> urls = value.url;
        int start = 0;
        for (int i = 0; i < titles.length; i++) {
            HomeCommonEntity tempValue = new HomeCommonEntity();
            tempValue.title = titles[i];
            tempValue.info = infos[i];
            tempValue.price = prices[i];
            tempValue.text = texts[i];
            tempValue.url = extractData(urls, start, 3);
            start += 3;

            values.add(tempValue);
        }
        return values;
    }


    private static ArrayList<String> extractData(ArrayList<String> source, int start, int interval) {
        ArrayList<String> tempUrls = new ArrayList<>();
        for (int i = start; i < start + interval; i++) {
            tempUrls.add(source.get(i));
        }
        return tempUrls;
    }


}
