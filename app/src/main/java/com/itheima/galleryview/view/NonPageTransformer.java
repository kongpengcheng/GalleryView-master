package com.itheima.galleryview.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Harry.Kong.
 * Time 2017/3/31.
 * Email kongpengcheng@aliyun.com.
 * Description:
 */
public class NonPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setScaleX(0.999f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}
