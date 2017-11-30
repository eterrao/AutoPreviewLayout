package com.welove.welove520.autopreviewlayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.vp_preview)
    ViewPager vpPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        PhotoBean photoBean = new PhotoBean();
        PhotoBean.Photo photo1 = new PhotoBean.Photo();
        photo1.setResourceId(R.drawable.timeline_top_image_1);
        PhotoBean.Photo photo2 = new PhotoBean.Photo();
        photo2.setResourceId(R.drawable.timeline_top_image_2);
        PhotoBean.Photo photo3 = new PhotoBean.Photo();
        photo3.setResourceId(R.drawable.timeline_top_image_3);
        List<PhotoBean.Photo> photoList = new ArrayList<>();
        photoList.add(photo1);
        photoList.add(photo2);
        photoList.add(photo3);
        photoBean.setPhotoList(photoList);
        final VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), photoBean);
        vpPreview.setAdapter(vpAdapter);
        vpPreview.setOffscreenPageLimit(3);
        vpPreview.setPageTransformer(false, new ViewPagerFadeTransformer());
        vpPreview.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Random randomDirection = new Random();
                ((AnimFragment) vpAdapter.getItem(position)).startScrollAnimation();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public static class VpAdapter extends FragmentStatePagerAdapter {

        private PhotoBean photoBean;

        public VpAdapter(FragmentManager fm, PhotoBean photos) {
            super(fm);
            this.photoBean = photos;
        }

        @Override
        public Fragment getItem(int position) {
            return AnimFragment.newInstance(photoBean, position);
        }

        @Override
        public int getCount() {
            return photoBean != null && photoBean.getPhotoList() != null ? photoBean.getPhotoList().size() : 0;
        }
    }
}
