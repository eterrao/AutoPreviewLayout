package com.welove.welove520.autopreviewlayout;

import android.content.Context;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.IOException;


/**
 * 和图片尺寸以及显示相关的工具类
 * Created by MontesLee
 * Date: 12-9-12
 * Time: 上午11:22
 */
public class ImageUtil {
    private static final String LOG_TAG = "ImageUtil";

    public static class ImageSize {
        private int width;
        private int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class LocalImage {
        private String path;
        private ImageSize imageSize;

        public LocalImage(String path, ImageSize imageSize) {
            this.path = path;
            this.imageSize = imageSize;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public ImageSize getImageSize() {
            return imageSize;
        }

    }

    /**
     * 根据实际的照片宽高计算出显示用的照片宽高。
     *
     * @param actualWidth
     * @param actualHeight
     * @return
     */
    public static ImageSize getPhotoDisplaySize(Context context, int actualWidth, int actualHeight) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = Math.round(actualHeight * displayWidth / (float) actualWidth);
        return new ImageSize(displayWidth, displayHeight);
    }

    /**
     * 根据实际的照片宽高计算出显示用的照片宽高。
     *
     * @param actualWidth
     * @param actualHeight
     * @return
     */
    public static ImageSize getChatPhotoDisplaySize(Context context, int actualWidth, int actualHeight) {
        int maxWidth = DensityUtil.dip2px(context, 140);
        int maxHeight = DensityUtil.dip2px(context, 160);
        if (actualWidth >= actualHeight) {
            int displayHeight = Math.round(actualHeight * maxWidth / (float) actualWidth);
            return new ImageUtil.ImageSize(maxWidth, displayHeight);
        } else {
            int displayWidth = Math.round(actualWidth * maxHeight / (float) actualHeight);
            return new ImageUtil.ImageSize(displayWidth, maxHeight);
        }
    }

    /**
     * 获取屏幕的长宽尺寸
     *
     * @return
     */
    public static ImageSize getScreenSize(Context context) {
        return new ImageSize(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels);
    }

    public static ExifInterface getExifInterface(String path) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(path);
        } catch (IOException e) {
            Log.e("ImageUtil.rotateImageView", "IOException get exif failed", e);
        } catch (IllegalArgumentException e) {
            Log.e("ImageUtil.rotateImageView", "IllegalArgumentException get exif failed", e);
        }

        return exifInterface;
    }

}
