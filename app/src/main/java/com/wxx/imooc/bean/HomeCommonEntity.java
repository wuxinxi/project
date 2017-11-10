package com.wxx.imooc.bean;

import java.util.ArrayList;

/**
 * 作者: Tangren on 2017-11-08
 * 包名：com.wxx.imooc.bean
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HomeCommonEntity {

    public HomeCommonEntity(int type, String logo, String title, String info, String price, String text, String site, String from, String zan, ArrayList<String> url) {
        this.type = type;
        this.logo = logo;
        this.title = title;
        this.info = info;
        this.price = price;
        this.text = text;
        this.site = site;
        this.from = from;
        this.zan = zan;
        this.url = url;
    }

    public HomeCommonEntity() {
    }

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;


}
