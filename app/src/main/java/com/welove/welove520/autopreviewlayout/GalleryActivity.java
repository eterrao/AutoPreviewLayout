package com.welove.welove520.autopreviewlayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.timeline_top_image_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startFrontAlphaOutAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivFront.setImageResource(photoList.get((new Random()).nextInt(3)).getResourceId());
        ivFront.startAnimation(animation);
        ivBack.bringToFront();
    }


    private void startBackScaleAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.timeline_top_image_anim2);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startBackAlphaOutAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivBack.setImageResource(photoList.get((new Random()).nextInt(3)).getResourceId());
        ivBack.startAnimation(animation);
        ivFront.bringToFront();
    }


    private void startFrontAlphaOutAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                startBackScaleAnimation();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivFront.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivFront.startAnimation(animation);
    }

    private void startBackAlphaOutAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                startFrontScaleAnimation();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivBack.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivBack.startAnimation(animation);
    }
}
