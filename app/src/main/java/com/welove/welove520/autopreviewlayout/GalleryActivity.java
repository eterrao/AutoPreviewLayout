package com.welove.welove520.autopreviewlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_front)
    ImageView ivFront;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ArrayList<PhotoBean.Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFrontScaleAnimation();
            }
        });

        PhotoBean.Photo photo1 = new PhotoBean.Photo();
        photo1.setResourceId(R.drawable.timeline_top_image_1);
        PhotoBean.Photo photo2 = new PhotoBean.Photo();
        photo2.setResourceId(R.drawable.timeline_top_image_2);
        PhotoBean.Photo photo3 = new PhotoBean.Photo();
        photo3.setResourceId(R.drawable.timeline_top_image_3);
        photoList = new ArrayList<>();
        photoList.add(photo1);
        photoList.add(photo2);
        photoList.add(photo3);

    }

    private void startFrontScaleAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.timeline_top_image_anim);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startFrontAlphaOutAnimation();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        ObjectAnimator translateY = ObjectAnimator.ofFloat(ivFront, "translationY", 0, -dip2px(320) * 0.3f)
                .setDuration(7000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivFront, "scaleX", 1, 1.8f)
                .setDuration(7000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivFront, "scaleY", 1, 1.8f)
                .setDuration(7000);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivFront.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startFrontAlphaOutAnimation();
            }
        });
        animatorSet.play(translateY).with(scaleX).with(scaleY);
        ivFront.setImageResource(photoList.get((new Random()).nextInt(3)).getResourceId());
//        ivFront.startAnimation(animation);
        animatorSet.start();
        ivBack.bringToFront();
    }


    private void startBackScaleAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.timeline_top_image_anim2);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startBackAlphaOutAnimation();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        ivBack.setImageResource(photoList.get((new Random()).nextInt(3)).getResourceId());
        ObjectAnimator translateY = ObjectAnimator.ofFloat(ivBack, "translationY", -dip2px(320) * 0.3f, dip2px(320) * 0.3f)
                .setDuration(7000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ivBack, "scaleX", 1, 1.8f)
                .setDuration(7000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ivBack, "scaleY", 1, 1.8f)
                .setDuration(7000);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivBack.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startBackAlphaOutAnimation();
            }
        });
        animatorSet.play(translateY).with(scaleX).with(scaleY);
        animatorSet.start();
//        ivBack.startAnimation(animation);
        ivFront.bringToFront();
    }


    private void startFrontAlphaOutAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha_out);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                startBackScaleAnimation();
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                ivFront.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        ivFront.startAnimation(animation);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(ivFront, "alpha", 1, 0)
                .setDuration(2000);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ivFront.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                startBackScaleAnimation();
            }
        });
        alphaAnimator.start();
    }

    private void startBackAlphaOutAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha_out);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                startFrontScaleAnimation();
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                ivBack.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        ivBack.startAnimation(animation);


        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(ivBack, "alpha", 1, 0)
                .setDuration(2000);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ivBack.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                startFrontScaleAnimation();
            }
        });
        alphaAnimator.start();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        Context context = getApplicationContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
