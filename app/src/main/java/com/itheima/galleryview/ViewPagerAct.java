package com.itheima.galleryview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.galleryview.view.ViewPagerTransform;

public class ViewPagerAct extends AppCompatActivity {
    private ViewPagerTransform viewPager;
    /**
     * 模拟viewpager的无线循环
     */
    private static final int COUNT = 1000000;
    private int[] mVirtualFridgePics;

    /**
     * 初始化虚拟冰箱的数据
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initVirtualFridgeData();
        viewPager = (ViewPagerTransform) findViewById(R.id.viewpager);


        int m = (COUNT / 2) % mVirtualFridgePics.length;
        int initPosition = COUNT / 2 - m;
        //默认展示第0个vp元素
        int startPos = initPosition + 1;
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                //模拟一个无线循环轮播
                return COUNT;
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
//            @Override
//            public float getPageWidth(int position) {
//                return 0.8f;
//            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int newPosition = position % mVirtualFridgePics.length;
                View view = View.inflate(getApplicationContext(), R.layout.widget_gallery_view, null);
//                view.setLayoutParams(getPageLayoutParams());
                ImageView iv = (ImageView) view.findViewById(R.id.headRIV);
                iv.setImageResource(mVirtualFridgePics[newPosition]);
                //  iv.setImageResource(position % 2 == 0 ? R.mipmap.a1 : R.mipmap.head);
                iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                if (iv.getParent() != null) {
                    ((ViewGroup) iv.getParent()).removeView(iv);
                }
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        viewPager.setCurrentItem(startPos);
    }

    private ViewPager.LayoutParams getPageLayoutParams() {
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = (int) (viewPager.getMeasuredWidth() * 0.85);
        layoutParams.height = viewPager.getMeasuredHeight();
        return layoutParams;
    }

    private void initVirtualFridgeData() {
        mVirtualFridgePics = new int[]{
                R.drawable.head,
                R.drawable.head,
                R.drawable.head
        };
    }

}
