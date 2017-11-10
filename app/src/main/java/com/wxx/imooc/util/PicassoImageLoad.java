package com.wxx.imooc.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者: Tangren on 2017-09-16
 * 包名：com.wxx.livetv.util.banner
 * 邮箱：996489865@qq.com
 * TODO:banner图片
 */

public class PicassoImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).fit().into(imageView);
    }
}
