package com.welove.welove520.autopreviewlayout;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.welove.welove520.autopreviewlayout.banner.Banner;
import com.welove.welove520.autopreviewlayout.banner.loader.AnimBannerImageLoderImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.banner_preview)
    Banner bannerPreview;

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
//        final VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), photoBean);
//        bannerPreview.setPageTransformer()
//        vpPreview.setAdapter(vpAdapter);
//        vpPreview.setOffscreenPageLimit(3);
//        bannerPreview.setPageTransformer(false, new ViewPagerFadeTransformer());
//        vpPreview.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Random randomDirection = new Random();
//                ((AnimFragment) vpAdapter.getItem(position)).startScrollAnimation();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        CardTransformer mTransformer = new CardTransformer(180, 0.15f);
        bannerPreview.setPageTransformer(false, new ViewPagerFadeTransformer());
        bannerPreview.setImageLoader(new AnimBannerImageLoderImpl())
                .setImages(photoList)
                .setDelayTime(10000)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        Log.e(TAG, "position = " + position + " , offset = " + positionOffset);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        View view = bannerPreview.getBannerView(position);
                        animateScroll(view);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                })
                .start();
    }

    private void animateScroll(View view) {
        if (view != null) {
            ImageView ivImage = (ImageView) view.findViewById(R.id.iv_image);
            final ScrollView svVertical = (ScrollView) view.findViewById(R.id.sv_vertical);
            final HorizontalScrollView svHorizontal = (HorizontalScrollView) view.findViewById(R.id.sv_horizontal);

//                            for (int i = 0; i < svVertical.getChildCount(); i++) {
//                                Log.e(TAG, "vertical  child =  " + svVertical.getChildAt(0).getClass().getSimpleName());
//                            }
//                            for (int i = 0; i < svHorizontal.getChildCount(); i++) {
//                                Log.e(TAG, "horizontal child =  " + svHorizontal.getChildAt(0).getClass().getSimpleName());
//                            }
//                            svVertical.smoothScrollTo(0, ivImage.getHeight());
//                            svHorizontal.smoothScrollTo(ivImage.getWidth(), 0);

            int[] scrollValues;
            if (svVertical.getScrollY() <= 0) {
                scrollValues = new int[]{svVertical.getScrollY(), ivImage.getHeight()};
            } else {
                scrollValues = new int[]{svVertical.getScrollY(), 0};
            }
            ValueAnimator animator = ValueAnimator.ofInt(scrollValues);
            animator.setDuration(10000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int scrollTo = (int) animation.getAnimatedValue();
                    Log.e(TAG, "scrollTo = " + scrollTo);
                    svVertical.scrollTo(0, scrollTo);
                }
            });
            animator.start();

            int[] scrollHorizontalValues;
            if (svHorizontal.getScrollX() <= 0) {
                scrollHorizontalValues = new int[]{svHorizontal.getScrollX(), ivImage.getWidth()};
            } else {
                scrollHorizontalValues = new int[]{svHorizontal.getScrollX(), 0};
            }
            ValueAnimator animatorHorizontal = ValueAnimator.ofInt(scrollHorizontalValues);
            animatorHorizontal.setDuration(10000);
            animatorHorizontal.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int scrollTo = (int) animation.getAnimatedValue();
                    Log.e(TAG, "scrollTo = " + scrollTo);
                    svHorizontal.scrollTo(scrollTo, 0);
                }
            });
            animatorHorizontal.start();
        }
    }

    public static class VpAdapter extends FragmentStatePagerAdapter {

        private PhotoBean photoBean;
        private AnimFragment fragment0;
        private AnimFragment fragment1;
        private AnimFragment fragment2;


        public VpAdapter(FragmentManager fm, PhotoBean photos) {
            super(fm);
            this.photoBean = photos;
        }

        @Override
        public Fragment getItem(int position) {
            return getItemByPosition(position);
        }

        private Fragment getItemByPosition(int position) {
            switch (position) {
                case 0:
                    if (fragment0 == null) {
                        fragment0 = AnimFragment.newInstance(photoBean, position);
                    }
                    return fragment0;
                case 1:
                    if (fragment1 == null) {
                        fragment1 = AnimFragment.newInstance(photoBean, position);
                    }
                    return fragment1;
                case 2:
                    if (fragment2 == null) {
                        fragment2 = AnimFragment.newInstance(photoBean, position);
                    }
                    return fragment2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return photoBean != null && photoBean.getPhotoList() != null ? photoBean.getPhotoList().size() : 0;
        }
    }
}
