package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Minh. Nguyen Le on 3/11/2016.
 */
public class ImageGetter implements Html.ImageGetter {

    private Context mContext;
    private TextView mTextView;

    public ImageGetter(Context context, TextView mTextView) {
        this.mContext = context;
        this.mTextView = mTextView;
    }

    @Override
    public Drawable getDrawable(String source) {
        int id = mContext.getResources().getIdentifier(source, "drawable", mContext.getPackageName());
        try {
            AnimationDrawable dra = (AnimationDrawable) ContextCompat.getDrawable(mContext, id);
            return dra != null ? resize(dra) : null;
        } catch (ClassCastException e) {
            Drawable dra = ContextCompat.getDrawable(mContext, id);
            return dra != null ? resize(dra) : null;
        }
    }

    private Drawable resize(Drawable dra) {
        dra.setBounds(0, 0, dra.getIntrinsicWidth(), dra.getIntrinsicHeight());

        UrlDrawable urlDrawable = new UrlDrawable();
        Rect rect;

        DisplayMetrics metrics = new DisplayMetrics();
        if (mContext != null) {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(metrics);
            }
        }
        float dpi = (int) metrics.density;
        int width = (int) (dra.getIntrinsicWidth() * dpi);
        int height = (int) (dra.getIntrinsicHeight() * dpi);
        if (height < mTextView.getLineHeight()) {
            width = width * mTextView.getLineHeight() / height;
            height = mTextView.getLineHeight();
        }
        rect = new Rect(0, 0, width, height);
        dra.setBounds(rect);

        urlDrawable.setBounds(rect);
        urlDrawable.setDrawable(dra);

        if (dra instanceof Animatable) {
            ((Animatable)dra).start();
        }

        mTextView.setText(mTextView.getText());
        mTextView.invalidate();
        return urlDrawable;
    }
}
