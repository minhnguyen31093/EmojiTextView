package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import pl.droidsonroids.gif.GifDrawable;


/**
 * Created by Minh. Nguyen Le on 3/3/2016.
 */
public class EmojiTextView extends TextView {

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
        ImageGetter imageGetter = new ImageGetter(context, this);
        CharSequence spanned = Html.fromHtml(text, glideImageGetter, null);
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

    private void handleAnimationDrawable(boolean isPlay) {
        CharSequence text = getText();
        if (text instanceof Spanned) {
            Spanned span = (Spanned) text;
            ImageSpan[] spans = span.getSpans(0, span.length() - 1, ImageSpan.class);
            for (ImageSpan s : spans) {
                Drawable d = s.getDrawable();
                if (d instanceof Animatable) {
                    Animatable animatable = (Animatable) d;
                    if (isPlay) {
                        animatable.start();
                    } else {
                        animatable.stop();
                    }
                } else if (d instanceof GifDrawable) {
                    GifDrawable animatable = (GifDrawable) d;
                    if (isPlay) {
                        animatable.start();
                    } else {
                        animatable.stop();
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