package com.welove.welove520.autopreviewlayout.banner.loader;

import android.content.Context;
import android.widget.ImageView;


public class TimelineBannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object url, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        if (url instanceof Integer) {
            imageView.setImageResource((Integer) url);
        } else if (url instanceof String) {
            String imageUrl = (String) url;
//            ImageLoaderManager.get().displayImage(ProxyServerUtils.getImageUrls(imageUrl), imageView, R.color.white, R.color.white, null);
        }
    }
}
