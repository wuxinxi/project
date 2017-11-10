package com.wxx.imooc.bean;

/**
 * 作者: Tangren on 2017-11-08
 * 包名：com.wxx.imooc.bean
 * 邮箱：996489865@qq.com
 * TODO:一句话描述
 */

public class FootEntity extends BaseEntity {


    public FootEntity(String title, String info, String from, String imageOne, String imageTwo, String destationUrl) {
        this.title = title;
        this.info = info;
        this.from = from;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.destationUrl = destationUrl;
    }

    /**
     * title : 5个新ios课程
     * info : jack大拿发布
     * from : 来自上海的讲师
     * imageOne : http://i7.hexunimg.cn/2015-07-07/177346079.jpg
     * imageTwo : http://imgsrc.baidu.com/forum/w%3D580/sign=2ca8123a261f95caa6f592bef9167fc5/bc1cae51f81986188dd8f8c049ed2e738ad4e6da.jpg
     * destationUrl : http://www.imooc.com/learn/677
     */



    private String title;
    private String info;
    private String from;
    private String imageOne;
    private String imageTwo;
    private String destationUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getDestationUrl() {
        return destationUrl;
    }

    public void setDestationUrl(String destationUrl) {
        this.destationUrl = destationUrl;
    }

}
