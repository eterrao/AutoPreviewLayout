package com.welove.welove520.autopreviewlayout.banner.loader;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.welove.welove520.autopreviewlayout.PhotoBean;
import com.welove.welove520.autopreviewlayout.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Raomengyang on 17-12-1.
 * Email    : ericrao@welove-inc.com
 * Desc     :
 * Version  : 1.0
 */

public class AnimBannerImageLoderImpl extends AnimBannerImageLoader {
    private static final String TAG = AnimBannerImageLoderImpl.class.getSimpleName();

    @Override
    public void displayImage(Context context, Object path, View view) {
        ViewHolder holder = new ViewHolder(view);
        PhotoBean.Photo photo = (PhotoBean.Photo) path;
        if (photo != null) {
            holder.ivImage.setImageResource(photo.getResourceId());
        }
//        startScrollAnimation(holder);
    }



    static class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.sv_horizontal)
        HorizontalScrollView svHorizontal;
        @BindView(R.id.sv_vertical)
        ScrollView svVertical;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
