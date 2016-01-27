package com.example.mylib_test.activity.custom_view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.example.mylib_test.R;
import com.example.mylib_test.activity.frag_viewpager_expand.ImageFragment;
import com.zone.banner_zonelib.ViewPagerCircle;
import com.zone.banner_zonelib.simpleadapter.PagerAdapterCircle_Image;

import java.util.ArrayList;
import java.util.List;
import and.abstractclass.BaseActvity;

/**
 * Created by Zone on 2016/1/27.
 */
public class ViewPagerRepeatWriteActivity extends BaseActvity {
    private ViewPagerCircle pager;
    private int[] imageResIDs = new int[] { R.drawable.akb, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e };

    @Override
    public void setContentView() {
        setContentView(R.layout.a_viewpager_circle_indicator);
    }

    @Override
    public void findIDs() {
        pager = (ViewPagerCircle)findViewById(R.id.pager);
    }

    @Override
    public void initData() {
        List<Integer> list=new ArrayList<Integer>();
        for (int i = 0; i < imageResIDs.length; i++) {
            list.add(imageResIDs[i]);
        }
        pager.setAdapter(new PagerAdapterCircle_Image(this, list) {
            @Override
            public void setImage(ImageView iv, int position) {
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(imageResIDs[position]);
            }
        });

    }

    @Override
    public void setListener() {
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                System.out.println("position");
                System.out.printf("onPageSelected====position:%d /t", position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.printf("onPageScrolled====position:%d /tpositionOffset:%f /tpositionOffsetPixels:%d /t", position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.printf("onPageScrollStateChanged====state:%d /t", state);

            }
        });
        pager.openTimeCircle();
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ImageFragment imageFrag = new ImageFragment();
            if (position < imageResIDs.length || position >= 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("path", imageResIDs[position]);
                imageFrag.setArguments(bundle);
                return imageFrag;
            }

            return null;
        }

        @Override
        public int getCount() {
            return imageResIDs.length;
        }
    }
}