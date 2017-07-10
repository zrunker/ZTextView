package cc.ibooker.ztextviewlib;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

/**
 * TextView文本管理类
 * 注意几个属性：
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)、
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前面包括，后面不包括)、
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，后面包括)、
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE(前后都包括)
 * Created by 邹峰立 on 2017/7/10.
 */
public class SpannableStringTextViewUtil {
    /**
     * 修改TextView原有字体颜色
     *
     * @param tv    TextView
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色 16进制
     */
    public static void updateForeColorSpan(TextView tv, int start, int end, String color) {
        try {
            String text = tv.getText().toString();
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            // ForegroundColorSpan为文字前景色
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
            builder.setSpan(colorSpan, start, end >= text.length() ? text.length() : end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义颜色
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     * @param color  文本颜色 16进制
     */
    public static void addForeColorSpan(TextView tv, String text, int tStart, int tEnd, String color) {
        try {
            SpannableString spanString = new SpannableString(text);
            // ForegroundColorSpan为文字前景色
            ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改TextView原有字体背景颜色
     *
     * @param tv    TextView
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色 16进制
     */
    public static void updateBackColorSpan(TextView tv, int start, int end, String color) {
        try {
            String text = tv.getText().toString();
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            // BackgroundColorSpan为文字背景色
            BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor(color));
            builder.setSpan(colorSpan, start, end >= text.length() ? text.length() : end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义背景颜色
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     * @param color  文本颜色 16进制
     */
    public static void addBackColorSpan(TextView tv, String text, int tStart, int tEnd, String color) {
        try {
            SpannableString spanString = new SpannableString(text);
            // BackgroundColorSpan为文字背景色
            BackgroundColorSpan span = new BackgroundColorSpan(Color.parseColor(color));
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义字体大小
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     * @param size   文本大小
     */
    public static void addFontSizeSpan(TextView tv, String text, int tStart, int tEnd, int size) {
        try {
            SpannableString spanString = new SpannableString(text);
            AbsoluteSizeSpan span = new AbsoluteSizeSpan(size);
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义超链接
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     * @param oper   所要进行的操作如："http://www.baidu.com"、"tel:10086"、"sms:10086"
     */
    public static void addUrlSpan(TextView tv, String text, int tStart, int tEnd, String oper) {
        try {
            SpannableString spanString = new SpannableString(text);
            URLSpan span = new URLSpan(oper);
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setMovementMethod(LinkMovementMethod.getInstance());//不设置，没有点击效果
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义点击效果
     *
     * @param tv                  要修改的TextView
     * @param text                添加的文本
     * @param tStart              修改开始的位置
     * @param tEnd                修改结束的位置
     * @param onClickSpanListener 所要进行的操作
     */
    public static void addClickableSpan(TextView tv, String text, int tStart, int tEnd, final OnClickSpanListener onClickSpanListener) {
        try {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            // 设置文字点击事件
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (onClickSpanListener != null)
                        onClickSpanListener.onClickSpan();
                }
            };
            builder.setSpan(clickableSpan, tStart, tEnd >= text.length() ? text.length() : tEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setMovementMethod(LinkMovementMethod.getInstance());//不设置，没有点击效果
            tv.append(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ClickableSpan点击监听接口
    public interface OnClickSpanListener {
        void onClickSpan();
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义粗体，斜体
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     */
    public static void addStyleSpan(TextView tv, String text, int tStart, int tEnd) {
        try {
            SpannableString spanString = new SpannableString(text);
            StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义粗体
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     */
    public static void addStyleBoldSpan(TextView tv, String text, int tStart, int tEnd) {
        try {
            SpannableString spanString = new SpannableString(text);
            StyleSpan span = new StyleSpan(Typeface.BOLD);
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义斜体
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     */
    public static void addStyleItalicSpan(TextView tv, String text, int tStart, int tEnd) {
        try {
            SpannableString spanString = new SpannableString(text);
            StyleSpan span = new StyleSpan(Typeface.ITALIC);
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义删除线
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     */
    public static void addStrikeSpan(TextView tv, String text, int tStart, int tEnd) {
        try {
            SpannableString spanString = new SpannableString(text);
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改原有文本删除线效果
     *
     * @param tv    要修改的TextView
     * @param start 修改开始的位置
     * @param end   修改结束的位置
     */
    public static void updateStrikeMySelfSpan(TextView tv, int start, int end) {
        try {
            String text = tv.getText().toString().trim();
            SpannableString spanString = new SpannableString(text);
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, start, end >= text.length() ? text.length() : end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加新文本，新文本可自定义下划线
     *
     * @param tv     要修改的TextView
     * @param text   添加的文本
     * @param tStart 修改开始的位置
     * @param tEnd   修改结束的位置
     */
    public static void addUnderLineSpan(TextView tv, String text, int tStart, int tEnd) {
        try {
            SpannableString spanString = new SpannableString(text);
            UnderlineSpan span = new UnderlineSpan();
            spanString.setSpan(span, tStart, tEnd >= text.length() ? text.length() : tEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在TextView文本最后添加图片
     *
     * @param tv 要修改的TextView
     * @param d  图片
     */
    public static void addImageSpan(TextView tv, Drawable d) {
        try {
            SpannableString spanString = new SpannableString(" ");
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.append(spanString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
