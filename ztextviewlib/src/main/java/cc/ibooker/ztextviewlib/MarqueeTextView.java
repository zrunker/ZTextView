package cc.ibooker.ztextviewlib;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 跑马灯TextView-自动聚焦
 * Created by 邹峰立 on 2017/6/12.
 */
public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
