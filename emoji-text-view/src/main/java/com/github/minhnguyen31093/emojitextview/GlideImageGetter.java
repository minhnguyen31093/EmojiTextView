package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by Minh. Nguyen Le on 3/4/2016.
 */
public class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {

    private final Context mContext;

    private final TextView mTextView;

    public static GlideImageGetter get(View view) {
        return (GlideImageGetter) view.getTag();
    }

    public GlideImageGetter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;

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
            Glide.with(mContext).load(id).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    Rect rect;

                    DisplayMetrics metrics = new DisplayMetrics();
                    ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
                    float dpi = (int) metrics.density;
                    int width = (int) (resource.getIntrinsicWidth() * dpi);
                    int height = (int) (resource.getIntrinsicHeight() * dpi);
                    if (height < mTextView.getLineHeight()) {
                        width = width * mTextView.getLineHeight() / height;
                        height = mTextView.getLineHeight();
                    }
                    rect = new Rect(0, 0, width, height);
                    resource.setBounds(rect);

                    urlDrawable.setBounds(rect);
                    urlDrawable.setDrawable(resource);

                    if (resource instanceof Animatable) {
                        Animatable animatable = (Animatable) resource;
                        urlDrawable.setCallback(get(mTextView));
                        animatable.start();
                    }

                    mTextView.setText(mTextView.getText());
                    mTextView.invalidate();
                }
            });
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

    public class GifTarget extends SimpleTarget<GifDrawable> {

        private final TextView textView;
        private final UrlDrawable mDrawable;

        private GifTarget(TextView textView, UrlDrawable drawable) {
            this.textView = textView;
            this.mDrawable = drawable;
        }

        @Override
        public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
            Rect rect;

            DisplayMetrics metrics = new DisplayMetrics();
            ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
            float dpi = (int) metrics.density;
            int width = (int) (resource.getIntrinsicWidth() * dpi);
            int height = (int) (resource.getIntrinsicHeight() * dpi);
            if (height < textView.getLineHeight()) {
                width = width * textView.getLineHeight() / height;
                height = textView.getLineHeight();
            }
            rect = new Rect(0, 0, width, height);
            resource.setBounds(rect);

            mDrawable.setBounds(rect);
            mDrawable.setDrawable(resource);

            Animatable animatable = (Animatable) resource;
            if (animatable.isRunning()) {
                mDrawable.setCallback(get(textView));
                animatable.start();
            }

            textView.setText(textView.getText());
            textView.invalidate();
        }
    }
}