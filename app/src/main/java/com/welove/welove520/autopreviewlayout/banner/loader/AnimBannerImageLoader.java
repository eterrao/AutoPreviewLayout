package com.welove.welove520.autopreviewlayout.banner.loader;

import android.content.Context;
import android.view.View;

import com.welove.welove520.autopreviewlayout.R;

/**
 * Created by Raomengyang on 17-12-1.
 * Email    : ericrao@welove-inc.com
 * Desc     :
 * Version  : 1.0
 */

public abstract class AnimBannerImageLoader implements ImageLoaderInterface {

    @Override
    public View createImageView(Context context) {
        View view = View.inflate(context, R.layout.item_viewpager, null);
        return view;
    }
}
