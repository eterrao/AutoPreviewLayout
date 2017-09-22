package com.welove.welove520.autopreviewlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_preview_container)
    FrameLayout flPreviewContainer;
    @BindView(R.id.iv_preview)
    ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ivPreview.setImageResource(R.drawable.timeline_top_image_1);

        doUpAnimation(ivPreview);
//        flPreviewContainer.addView(ivPreview, getCoverLayoutParams(640, 640));
    }

    private void doUpAnimation(final View ivPreview) {
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(ivPreview, "scaleX", 1f, 3f);
        scaleXAnim.setDuration(10000);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(ivPreview, "scaleY", 1f, 3f);
        scaleYAnim.setDuration(10000);
        scaleXAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleYAnim.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(ivPreview, "translationY", 0, 400);
        translateAnim.setDuration(10000);
        translateAnim.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator alphaFadeInAnim = ObjectAnimator.ofFloat(ivPreview, "alpha", 0f, 1f);
        alphaFadeInAnim.setDuration(300);
        ObjectAnimator alphaFadeOutAnim = ObjectAnimator.ofFloat(ivPreview, "alpha", 1f, 0f);
        alphaFadeOutAnim.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnim, scaleYAnim, translateAnim);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playSequentially(animatorSet);
        animatorSet.setDuration(5000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Glide.with(getApplicationContext()).load(getImgResourceId()).centerCrop().crossFade().into((ImageView) ivPreview);
                doDownAnimation(ivPreview);
            }
        });
//        animatorSet.start();
        animatorSet1.start();
    }

    private void doDownAnimation(final View ivPreview) {
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(ivPreview, "scaleX", 3f, 1f);
        scaleXAnim.setDuration(5000);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(ivPreview, "scaleY", 3f, 1f);
        scaleYAnim.setDuration(5000);
        scaleXAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleYAnim.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator translateAnim = ObjectAnimator.ofFloat(ivPreview, "translationY", 400, 0);
        translateAnim.setDuration(5000);
        translateAnim.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator alphaFadeInAnim = ObjectAnimator.ofFloat(ivPreview, "alpha", 0f, 1f);
        alphaFadeInAnim.setDuration(300);
        ObjectAnimator alphaFadeOutAnim = ObjectAnimator.ofFloat(ivPreview, "alpha", 1f, 0f);
        alphaFadeOutAnim.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnim, scaleYAnim, translateAnim);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playSequentially(animatorSet);
        animatorSet.setDuration(5000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Glide.with(getApplicationContext()).load(getImgResourceId()).centerCrop().crossFade().into((ImageView) ivPreview);
                doUpAnimation(ivPreview);
            }
        });
//        animatorSet.start();
        animatorSet1.start();
    }

    private FrameLayout.LayoutParams getCoverLayoutParams(int imageWidth, int imageHeight) {
        float srcWidth = imageWidth;
        float srcHeight = imageHeight;
        float minWidth = (int) (ImageUtil.getScreenSize(getApplicationContext()).getWidth() * 1.1f);
        float minHeight = (int) (DensityUtil.dip2px(getApplicationContext(), 180) * 1.1f);
        float srcRatio = srcWidth / srcHeight;
        float outRatio = minWidth / minHeight;
        float actualOutWidth = srcWidth;
        float actualOutHeight = srcHeight;
        if (actualOutWidth < minWidth) {
            actualOutWidth = minWidth;
            actualOutHeight = actualOutWidth / srcRatio;
        }
        if (actualOutHeight < minHeight) {
            actualOutHeight = minHeight;
            actualOutWidth = actualOutHeight * srcRatio;
        }
        return new FrameLayout.LayoutParams((int) actualOutWidth, (int) actualOutHeight, Gravity.TOP);
    }

    private Integer getImgResourceId() {
        List<Integer> defaultImageResIdList = new ArrayList<Integer>();
        defaultImageResIdList.add(R.drawable.timeline_top_image_1);
        defaultImageResIdList.add(R.drawable.timeline_top_image_2);
        defaultImageResIdList.add(R.drawable.timeline_top_image_3);
        return defaultImageResIdList.get(new Random().nextInt(2));
    }
}
