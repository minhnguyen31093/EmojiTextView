package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Minh. Nguyen Le on 3/4/2016.
 */
public class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {

    private final Context mContext;

    private final TextView mTextView;

    private final Set<ImageGetterViewTarget> mTargets;

    public static GlideImageGetter get(View view) {
        return (GlideImageGetter)view.getTag();
    }

    public void clear() {
        if (mTextView != null) {
            GlideImageGetter prev = get(mTextView);
            if (prev == null) return;

            for (ImageGetterViewTarget target : prev.mTargets) {
                Glide.clear(target);
            }
        }
    }

    public GlideImageGetter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;

        clear();
        mTargets = new HashSet<>();
        if (mTextView != null) {
            mTextView.setTag(this);
        }
    }

    @Override
    public Drawable getDrawable(String url) {
        int id = mContext.getResources().getIdentifier(url, "drawable", mContext.getPackageName());
        final UrlDrawable urlDrawable = new UrlDrawable();
        System.out.println("Downloading from: " + url);
        if (mTextView != null) {
            Glide.with(mContext).load(id)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new ImageGetterViewTarget(mTextView, urlDrawable));
        }
        return urlDrawable;

    }

    @Override
    public void invalidateDrawable(Drawable who) {
        if (mTextView != null) {
            mTextView.invalidate();
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {

    }

    private class ImageGetterViewTarget extends ViewTarget<TextView, GlideDrawable> {

        private final UrlDrawable mDrawable;

        private ImageGetterViewTarget(TextView view, UrlDrawable drawable) {
            super(view);
            mTargets.add(this);
            this.mDrawable = drawable;
        }

        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
            Rect rect;

            DisplayMetrics metrics = new DisplayMetrics();
            ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
            float dpi = (int) metrics.density;
            int width = (int) (resource.getIntrinsicWidth() * dpi);
            int height = (int) (resource.getIntrinsicHeight() * dpi);
            if (height < getView().getLineHeight()) {
                width = width * getView().getLineHeight() / height;
                height = getView().getLineHeight();
            }
            rect = new Rect(0, 0, width, height);
            resource.setBounds(rect);

            mDrawable.setBounds(rect);
            mDrawable.setDrawable(resource);


            if (resource.isAnimated()) {
                mDrawable.setCallback(get(getView()));
                resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
                resource.start();
            }

            getView().setText(getView().getText());
            getView().invalidate();
        }

        private Request request;
        @Override
        public Request getRequest() {
            return request;
        }

        @Override
        public void setRequest(Request request) {
            this.request = request;
        }
    }
}