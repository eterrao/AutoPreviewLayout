package com.welove.welove520.autopreviewlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Raomengyang on 17-11-30.
 * Email    : ericrao@welove-inc.com
 * Desc     :
 * Version  : 1.0
 */

public class AnimFragment extends Fragment {

    private static final String TAG = AnimFragment.class.getSimpleName();
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.sv_horizontal)
    HorizontalScrollView svHorizontal;
    @BindView(R.id.sv_vertical)
    ScrollView svVertical;
    Unbinder unbinder;
    private PhotoBean photoBean;
    private int position;

    public static AnimFragment newInstance(PhotoBean photos, int position) {
        AnimFragment animFragment = new AnimFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("photos", photos);
        bundle.putInt("position", position);
        animFragment.setArguments(bundle);
        return animFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_viewpager, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            photoBean = (PhotoBean) bundle.getSerializable("photos");
            position = bundle.getInt("position");
            if (photoBean != null) {
                if (position >= 0) {
                    Glide.with(getContext())
                            .load(photoBean.getPhotoList().get(position).getResourceId())
                            .dontAnimate()
                            .into(ivImage);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void startScrollAnimation() {
//        for (int i = 0; i < svVertical.getChildCount(); i++) {
//            Log.e(TAG, "vertical  child =  " + svVertical.getChildAt(0).getClass().getSimpleName());
//        }
//        for (int i = 0; i < svHorizontal.getChildCount(); i++) {
//            Log.e(TAG, "horizontal child =  " + svHorizontal.getChildAt(0).getClass().getSimpleName());
//        }
//        if (svVertical.getChildAt(0) instanceof HorizontalScrollView) {
        svVertical.smoothScrollTo(0, ivImage.getHeight());
//        }
//        if (svHorizontal.getChildAt(0) instanceof ImageView) {
        svHorizontal.smoothScrollTo(ivImage.getWidth(), 0);
//        }
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(ivImage, "translationY",)
    }
}
