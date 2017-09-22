package com.welove.welove520.autopreviewlayout;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class ResourceUtil {

    /**
     * @param resId string.xml里面的id
     * @return
     */
    public static String getStr(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getFormatStr(Context context, int resId, Object... args) {
        String format = ResourceUtil.getStr(context, resId);
        return String.format(format, args);
    }

    /**
     * color.xml里面的id
     *
     * @param colorResId
     * @return
     */
    public static int getColor(Context context, int colorResId) {
        return ContextCompat.getColor(context, colorResId);
    }

    public static int getDimen(Context context, int dimenResId) {
        return context.getResources().getDimensionPixelSize(dimenResId);
    }

    public static Drawable getDrawable(Context context, int drawableResId) {
        return ContextCompat.getDrawable(context, drawableResId);
    }

    public static AnimationDrawable getAnimation(Context context, int animResId) {
        Resources res = context.getResources();
        AnimationDrawable animDraw = null;
        try {
            animDraw = (AnimationDrawable)
                    AnimationDrawable.createFromXml(res, res.getAnimation(animResId));
        } catch (XmlPullParserException | IOException e) {
            /* TODO Auto-generated catch block */
            e.printStackTrace();
        }
        return animDraw;
    }

    public static Bitmap getBitmap(Context context, int resId) {
        Resources resources = context.getResources();
        TypedValue value = new TypedValue();
        resources.openRawResource(resId, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, resId, opts);
    }

    public static Integer getInteger(Context context, int resId) {
        Resources res = context.getResources();
        return res.getInteger(resId);
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

}
