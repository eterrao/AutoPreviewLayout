package com.welove.welove520.autopreviewlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {

    private static final int[] IMAGES = {
            R.drawable.timeline_top_image_1,
            R.drawable.timeline_top_image_2,
            R.drawable.timeline_top_image_3
    };
    private static int VIEWPAGER_STATE = ViewPager.SCROLL_STATE_IDLE;
    @BindView(R.id.vp_preview)
    ViewPager vpPreview;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private AutoParallexVPAdapter vpAdapter;
    private List<View> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initViewPager();
        initFloatingButton();
    }

    private void initViewPager() {
        imageList = new ArrayList<>();
        vpAdapter = new AutoParallexVPAdapter(imageList);
        vpPreview.setAdapter(vpAdapter);
//        vpPreview.setPageTransformer(false, new CardTransformer(180, 0.2f));
        vpPreview.setPageTransformer(false, new ViewPagerFadeTransformer());
        vpPreview.addOnPageChangeListener(pageChangeListener);
        setDatas();
    }

    private void setDatas() {
        for (int resId : IMAGES) {
            View view = View.inflate(this, R.layout.layout_auto_scroll, null);
            ViewHolder holder = new ViewHolder(view);
            holder.ivAudoScroll.setImageResource(resId);
            imageList.add(view);
        }
        vpAdapter.notifyDataSetChanged();
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            if (imageList.get(position) instanceof ScrollView && VIEWPAGER_STATE == ViewPager.SCROLL_STATE_SETTLING) {
                ScrollView scrollView = (ScrollView) imageList.get(position);
                for (int index = 0; index < scrollView.getChildCount(); index++) {
                    if (scrollView.getChildAt(index) instanceof HorizontalScrollView) {
                        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) scrollView.getChildAt(index);
                        final ImageView imageView = (ImageView) horizontalScrollView.getChildAt(0);
                        ObjectAnimator yTranslate = ObjectAnimator.ofInt(scrollView, "scrollY", 0, imageView.getHeight());
                        ObjectAnimator xTranslate = ObjectAnimator.ofInt(horizontalScrollView, "scrollX", 0, imageView.getWidth());
                        xTranslate.setDuration(3000);
                        yTranslate.setDuration(3000);
                        yTranslate.setInterpolator(new DecelerateInterpolator());
                        xTranslate.setInterpolator(new DecelerateInterpolator());
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(xTranslate, yTranslate);
                        animatorSet.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (position >= imageList.size() - 1) {
                                    vpPreview.setCurrentItem(0);
                                } else {
                                    vpPreview.setCurrentItem(position + 1);
                                }
                            }

                            @Override
                            public void onAnimationStart(Animator animation) {
                            }
                        });
                        animatorSet.start();
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            VIEWPAGER_STATE = state;
        }
    };

    private void initFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    static class AutoParallexVPAdapter extends PagerAdapter {

        private List<View> imageViewList;

        public AutoParallexVPAdapter(List<View> imageViews) {
            this.imageViewList = imageViews;
        }

        @Override
        public int getCount() {
            return imageViewList != null ? imageViewList.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if (imageViewList != null && imageViewList.size() > 0) {
                view = imageViewList.get(position);
                container.addView(view);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_audo_scroll)
        ImageView ivAudoScroll;
        @BindView(R.id.hsv_auto_scroll)
        HorizontalScrollView hsvAutoScroll;
        @BindView(R.id.sv_auto_scroll)
        ScrollView svAutoScroll;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
