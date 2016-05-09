package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

/**
 * Created by Minh. Nguyen Le on 3/11/2016.
 */
public class GifImageGetter implements Html.ImageGetter {
    private Context context;

    public GifImageGetter (Context context) {
        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {
        int id = context.getResources().getIdentifier(source, "drawable", context.getPackageName());
        AnimationDrawable dra = (AnimationDrawable)context.getResources().getDrawable(id);
        dra.setBounds(0, 0, dra.getIntrinsicWidth(), dra.getIntrinsicHeight());
        dra.start();
        return dra;
    }
}
