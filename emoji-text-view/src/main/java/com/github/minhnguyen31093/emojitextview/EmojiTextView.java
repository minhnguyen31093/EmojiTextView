package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Minh. Nguyen Le on 3/3/2016.
 */
public class EmojiTextView extends TextView implements Drawable.Callback {
    private Context context;

    public EmojiTextView(Context context) {
        super(context);
        this.context = context;
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public EmojiTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void setEmojiText(String text) {
        text = EmojiUtils.convertTag(text);
        GlideImageGetter glideImageGetter = new GlideImageGetter(context, this);
        GifImageGetter gifImageGetter = new GifImageGetter(context);
        CharSequence spanned = Html.fromHtml(text, gifImageGetter, null);
        setText(spanned);
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handleAnimationDrawable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handleAnimationDrawable(false);
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        invalidate();
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        if (who != null && what != null) {
//            mHandler.postAtTime(what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        if (who != null && what != null) {
//            mHandler.removeCallbacks(what);
        }
    }

    private void handleAnimationDrawable(boolean isPlay) {
        CharSequence text = getText();
        if (text instanceof Spanned) {
            Spanned span = (Spanned) text;
            ImageSpan[] spans = span.getSpans(0, span.length() - 1, ImageSpan.class);
            for (ImageSpan s : spans) {
                Drawable d = s.getDrawable();
                if (d instanceof AnimationDrawable) {
                    AnimationDrawable animationDrawable = (AnimationDrawable) d;
                    if (isPlay) {
                        animationDrawable.setCallback(this);
                        animationDrawable.start();
                    } else {
                        animationDrawable.stop();
                        animationDrawable.setCallback(null);
                    }
                }
            }
        }
    }

    public void setIsAll() {
        EmojiUtils.setIsAll();
    }

    public void setIsYahoo() {
        EmojiUtils.setIsYahoo();
    }

    public void setIsFacebook() {
        EmojiUtils.setIsFacebook();
    }

    public void setIsSkype() {
        EmojiUtils.setIsSkype();
    }
}