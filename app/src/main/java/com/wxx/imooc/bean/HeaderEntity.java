package com.wxx.imooc.bean;

import java.util.List;

/**
 * 作者: Tangren on 2017-11-08
 * 包名：com.wxx.imooc.bean
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class HeaderEntity extends BaseEntity {

    public List<String> photoUrls;
    public List<String> imgs;

    public HeaderEntity(List<String> photoUrls, List<String> imgs) {
        this.photoUrls = photoUrls;
        this.imgs = imgs;
    }
}
