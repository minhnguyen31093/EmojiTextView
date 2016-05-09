package com.github.minhnguyen31093.emojitextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Minh. Nguyen Le on 3/3/2016.
 */
public class EmojiUtils {

    private static boolean isAll = true;
    private static boolean isYahoo = false;
    private static boolean isFacebook = false;
    private static boolean isSkype = false;

    public static String convertTag(String str) {
        str = str.replaceAll("&amp;quot;", "\"");

        if (isAll || (!isAll && isYahoo)) {
            str = str.replaceAll(">:\\)", "<img src=\"yahoo_19\"/>")
                    .replaceAll(":\\(\\(", "<img src=\"yahoo_20\"/>")
                    .replaceAll(":\\)\\)", "<img src=\"yahoo_21\"/>")
                    .replaceAll("/:\\)", "<img src=\"yahoo_23\"/>")
                    .replaceAll(":\\)]", "<img src=\"yahoo_29\"/>")
                    .replaceAll(":\\)", "<img src=\"yahoo_1\"/>")
                    .replaceAll(":\\(", "<img src=\"yahoo_2\"/>")
                    .replaceAll(";;\\)", "<img src=\"yahoo_5\"/>")
                    .replaceAll(";\\)", "<img src=\"yahoo_3\"/>")
                    .replaceAll(">:D<", "<img src=\"yahoo_6\"/>")
                    .replaceAll(":D", "<img src=\"yahoo_4\"/>")
                    .replaceAll(":-/", "<img src=\"yahoo_7\"/>")
                    .replaceAll(":x", "<img src=\"yahoo_8\"/>")
                    .replaceAll(":\">", "<img src=\"yahoo_9\"/>")
                    .replaceAll(">:P", "<img src=\"yahoo_104\"/>")
                    .replaceAll(":P", "<img src=\"yahoo_10\"/>")
                    .replaceAll(":-\\*", "<img src=\"yahoo_11\"/>")
                    .replaceAll("=\\(\\(", "<img src=\"yahoo_12\"/>")
                    .replaceAll(":-O", "<img src=\"yahoo_13\"/>")
                    .replaceAll("~X\\(", "<img src=\"yahoo_30\"/>")
                    .replaceAll("X\\(", "<img src=\"yahoo_14\"/>")
                    .replaceAll(":>", "<img src=\"yahoo_15\"/>")
                    .replaceAll("B-\\)", "<img src=\"yahoo_16\"/>")
                    .replaceAll("#:-S", "<img src=\"yahoo_18\"/>")
                    .replaceAll(":-SS", "<img src=\"yahoo_48\"/>")
                    .replaceAll(":-S", "<img src=\"yahoo_17\"/>")
                    .replaceAll("\\(:\\|", "<img src=\"yahoo_43\"/>")
                    .replaceAll(":\\|", "<img src=\"yahoo_22\"/>")
                    .replaceAll("=\\)\\)", "<img src=\"yahoo_24\"/>")
                    .replaceAll("O:-\\)", "<img src=\"yahoo_25\"/>")
                    .replaceAll(":-B", "<img src=\"yahoo_26\"/>")
                    .replaceAll("=;", "<img src=\"yahoo_27\"/>")
                    .replaceAll(":-c", "<img src=\"yahoo_28\"/>")
                    .replaceAll(":-h", "<img src=\"yahoo_31\"/>")
                    .replaceAll(":-t", "<img src=\"yahoo_32\"/>")
                    .replaceAll("8->", "<img src=\"yahoo_33\"/>")
                    .replaceAll("I-\\)", "<img src=\"yahoo_34\"/>")
                    .replaceAll("8-\\|", "<img src=\"yahoo_35\"/>")
                    .replaceAll("L-\\)", "<img src=\"yahoo_36\"/>")
                    .replaceAll(":-&", "<img src=\"yahoo_37\"/>")
                    .replaceAll(":-\\$", "<img src=\"yahoo_38\"/>")
                    .replaceAll("\\[-\\(", "<img src=\"yahoo_39\"/>")
                    .replaceAll(":O\\)", "<img src=\"yahoo_40\"/>")
                    .replaceAll("8-\\u007D", "<img src=\"yahoo_41\"/>")
                    .replaceAll("<:-P", "<img src=\"yahoo_42\"/>")
                    .replaceAll("=P~", "<img src=\"yahoo_44\"/>")
                    .replaceAll(":-\\?", "<img src=\"yahoo_45\"/>")
                    .replaceAll("#-o", "<img src=\"yahoo_46\"/>")
                    .replaceAll("=D>", "<img src=\"yahoo_47\"/>")
                    .replaceAll("@-\\)", "<img src=\"yahoo_100\"/>")
                    .replaceAll(":\\^o", "<img src=\"yahoo_101\"/>")
                    .replaceAll(":-w", "<img src=\"yahoo_102\"/>")
                    .replaceAll(":-<", "<img src=\"yahoo_103\"/>")
                    .replaceAll("<\\):\\)", "<img src=\"yahoo_105\"/>")
                    .replaceAll("X_X", "<img src=\"yahoo_109\"/>")
                    .replaceAll(":!!", "<img src=\"yahoo_110\"/>")
                    .replaceAll("\\\\m/", "<img src=\"yahoo_111\"/>")
                    .replaceAll(":-q", "<img src=\"yahoo_112\"/>")
                    .replaceAll(":-bd", "<img src=\"yahoo_113\"/>")
                    .replaceAll("\\^#\\(\\^", "<img src=\"yahoo_114\"/>")
                    .replaceAll(":ar!", "<img src=\"yahoo_pirate_2\"/>");
        }

        if (isAll || (!isAll && isFacebook)) {
            str = str.replaceAll("O:\\)", "<img src=\"facebook_6\"/>")
                    .replaceAll("3:\\)", "<img src=\"facebook_7\"/>")
                    .replaceAll(":\\)", "<img src=\"facebook_1\"/>")
                    .replaceAll(":D", "<img src=\"facebook_2\"/>")
                    .replaceAll(">:\\(", "<img src=\"facebook_20\"/>")
                    .replaceAll(":\\(", "<img src=\"facebook_3\"/>")
                    .replaceAll(":'\\(", "<img src=\"facebook_4\"/>")
                    .replaceAll(":P", "<img src=\"facebook_5\"/>")
                    .replaceAll("o.O", "<img src=\"facebook_8\"/>")
                    .replaceAll(";\\)", "<img src=\"facebook_9\"/>")
                    .replaceAll(">:O", "<img src=\"facebook_12\"/>")
                    .replaceAll(":O", "<img src=\"facebook_10\"/>")
                    .replaceAll("-_-", "<img src=\"facebook_11\"/>")
                    .replaceAll(":\\*", "<img src=\"facebook_13\"/>")
                    .replaceAll("<3", "<img src=\"facebook_14\"/>")
                    .replaceAll("\\^_\\^", "<img src=\"facebook_15\"/>")
                    .replaceAll("8-\\)", "<img src=\"facebook_16\"/>")
                    .replaceAll("8\\|", "<img src=\"facebook_17\"/>")
                    .replaceAll("\\(\\^\\^\\^\\)", "<img src=\"facebook_18\"/>")
                    .replaceAll(":\\|\\]", "<img src=\"facebook_19\"/>")
                    .replaceAll(":v", "<img src=\"facebook_21\"/>")
                    .replaceAll(":/", "<img src=\"facebook_22\"/>")
                    .replaceAll(":3", "<img src=\"facebook_23\"/>")
                    .replaceAll("☺", "<img src=\"facebook_24\"/>")
                    .replaceAll("\\(y\\)", "<img src=\"facebook_25\"/>")
                    .replaceAll("\uD83D\uDE0D", "<img src=\"facebook_26\"/>")
                    .replaceAll(":poop:", "<img src=\"facebook_26\"/>");
        }

        if (isAll || (!isAll && isSkype)) {
            //TODO
        }

        return str;
    }

    public static CharSequence getEmojiSpanned(final Context context, TextView txt, String str) {
        str = EmojiUtils.convertTag(str);
        if (txt != null) {
            GlideImageGetter glideImageGetter = new GlideImageGetter(context, txt);
            return Html.fromHtml(str, glideImageGetter, null);
        } else {
            Html.ImageGetter imageGetter = new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    int id = context.getResources().getIdentifier(source, "drawable", context.getPackageName());
                    Drawable emoji = context.getResources().getDrawable(id);
                    DisplayMetrics metrics = new DisplayMetrics();
                    ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
                    float dpi = (int) metrics.density;
                    int width = (int) (emoji.getIntrinsicWidth() * dpi);
                    int height = (int) (emoji.getIntrinsicHeight() * dpi);
                    emoji.setBounds(0, 0, width, height);
                    return emoji;
                }
            };
            return Html.fromHtml(str, imageGetter, null);
        }
    }


    public static void setIsAll() {
        isAll = true;
        isYahoo = false;
        isFacebook = false;
        isSkype = false;
    }

    public static void setIsYahoo() {
        isYahoo = true;
        isAll = false;
        isFacebook = false;
        isSkype = false;
    }

    public static void setIsFacebook() {
        isFacebook = true;
        isAll = false;
        isYahoo = false;
        isSkype = false;
    }

    public static void setIsSkype() {
        EmojiUtils.isSkype = true;
        isAll = false;
        isYahoo = false;
        isFacebook = false;
    }
}
