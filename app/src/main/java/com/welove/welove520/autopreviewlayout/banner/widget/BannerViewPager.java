package com.welove.welove520.autopreviewlayout.banner.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class BannerViewPager extends ViewPager {

    private static final String LOG_TAG = "BannerViewPager";
    private static final boolean DEBUG = true;

    private boolean scrollable = true;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean superResult = super.onTouchEvent(ev);
        if (DEBUG) {
            Log.d(LOG_TAG, "onTouchEvent " + superResult);
        }
        return this.scrollable && superResult;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean superResult = super.onInterceptTouchEvent(ev);
        if (DEBUG) {
            Log.d(LOG_TAG, "onInterceptTouchEvent " + this.scrollable + " " + superResult);
        }
        return this.scrollable && superResult;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);//防止parent拦截touch事件
        boolean superResult = super.dispatchTouchEvent(ev);
        if (DEBUG) {
            Log.d(LOG_TAG, "dispatchTouchEvent " + superResult);
        }
        return superResult;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (DEBUG) {
            Log.d(LOG_TAG, "onInterceptHoverEvent");
        }
        return super.onInterceptHoverEvent(event);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
}
