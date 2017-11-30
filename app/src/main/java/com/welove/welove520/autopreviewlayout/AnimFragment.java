package com.welove.welove520.autopreviewlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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
        View view = View.inflate(container.getContext(), R.layout.item_viewpager, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
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
    }
}
