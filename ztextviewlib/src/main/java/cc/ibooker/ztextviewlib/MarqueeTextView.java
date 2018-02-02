package cc.ibooker.ztextviewlib;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * 跑马灯TextView-自动聚焦
 * Created by 邹峰立 on 2017/6/12.
 */
public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化
    private void init() {
        // 设置文本超出部分模式
        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // 设置跑马灯重复次数，-1为无限重复
        this.setMarqueeRepeatLimit(-1);
        // 单行显示
        this.setSingleLine(true);
        this.setMaxLines(1);
    }

    // 焦点 聚焦
    @Override
    public boolean isFocused() {
        return true;
    }
}
