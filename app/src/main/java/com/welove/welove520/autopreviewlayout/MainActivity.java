package com.welove.welove520.autopreviewlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int FADE_DURATION = 500;
    private static final float TRANSLATION_X = 100F;
    private static final float TRANSLATION_Y = 100F;
    private static final long ANIMATION_DURATION_MS = 1000;
    @BindView(R.id.iv_preview)
    ImageView ivPreview;
    @BindView(R.id.iv_preview_bottom)
    ImageView ivPreviewBottom;
    @BindView(R.id.fl_preview_container)
    RelativeLayout flPreviewContainer;
    @BindView(R.id.sv_preview)
    ScrollView svPreview;
    @BindView(R.id.hsv_preview)
    HorizontalScrollView hsvPreview;

    private int currentIndex = 0;
    private ArrayList<Integer> defaultImageResIdList;
    private AnimatorSet togetherSet;
    private AnimatorSet sequenceSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void doScrollAnimation(final ImageView animationView, final float direction) {
        animationView.setVisibility(View.VISIBLE);
        animationView.setImageResource(getImgResourceId());
        final ObjectAnimator translateAnim = ObjectAnimator.ofFloat(animationView, "translationY", 0, direction);
        ObjectAnimator alphaIn = ObjectAnimator.ofFloat(animationView, "alpha", 0.2f, 1f);
        ObjectAnimator alphaOut = ObjectAnimator.ofFloat(animationView, "alpha", 1f, 0);
        translateAnim.setDuration(ANIMATION_DURATION_MS);
        alphaIn.setDuration(FADE_DURATION);
        alphaOut.setDuration(FADE_DURATION);
        translateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ValueAnimator anim = animation;
                if (anim != null) {
                    if (svPreview != null) {
                        if (svPreview.getChildCount() > 0) {
                            int scrollY = 0;
                            if (direction > 0) {
                                scrollY = (int) (svPreview.getChildAt(0).getHeight() / TRANSLATION_Y * ((float) anim.getAnimatedValue()));
                            } else {
                                scrollY = svPreview.getChildAt(0).getHeight() + ((int) (svPreview.getChildAt(0).getHeight() / TRANSLATION_Y * ((float) animation.getAnimatedValue())));
                            }
                            svPreview.scrollTo(0, scrollY);
                        }
                    }
                    if (hsvPreview != null) {
                        if (hsvPreview.getChildCount() > 0) {
                            int scrollX = 0;
                            if (direction > 0) {
                                scrollX = (int) (hsvPreview.getChildAt(0).getWidth() / TRANSLATION_X * ((float) anim.getAnimatedValue()));
                            } else {
                                scrollX = hsvPreview.getChildAt(0).getWidth() + ((int) (hsvPreview.getChildAt(0).getWidth() / TRANSLATION_X * ((float) animation.getAnimatedValue())));
                            }
                            hsvPreview.scrollTo(scrollX, 0);
                        }
                    }
                }
            }
        });
        alphaOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (direction > 0) {
                    doScrollAnimation(ivPreview, -TRANSLATION_Y);
                } else {
                    doScrollAnimation(ivPreviewBottom, TRANSLATION_Y);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (direction > 0) {
                    animationView.setVisibility(View.INVISIBLE);
                }
            }
        });
        togetherSet = new AnimatorSet();
        togetherSet.playTogether(alphaIn, translateAnim);
        sequenceSet = new AnimatorSet();
        sequenceSet.playSequentially(togetherSet, alphaOut);
        sequenceSet.start();
    }

    private Integer getImgResourceId() {
        if (defaultImageResIdList == null) {
            defaultImageResIdList = new ArrayList<Integer>();
            defaultImageResIdList.add(R.drawable.timeline_top_image_1);
            defaultImageResIdList.add(R.drawable.timeline_top_image_2);
            defaultImageResIdList.add(R.drawable.timeline_top_image_3);
        }
        if (currentIndex < defaultImageResIdList.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        return defaultImageResIdList.get(currentIndex);
    }

    public void startAnimation(View view) {
        if (togetherSet != null) {
            togetherSet.cancel();
        }
        if (sequenceSet != null) {
            sequenceSet.cancel();
        }
        doScrollAnimation(ivPreview, TRANSLATION_Y);
    }
}
