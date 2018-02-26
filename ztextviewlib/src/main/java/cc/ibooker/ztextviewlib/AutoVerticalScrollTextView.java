package cc.ibooker.ztextviewlib;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * 自动垂直滚动的TextView
 */
public class AutoVerticalScrollTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private Context mContext;
    // mInUp,mOutUp分别构成向下翻页的进出动画
    private Rotate3dAnimation mInUp;
    private Rotate3dAnimation mOutUp;

    private float textSize = 13; // 设置字体大小
    private int textColor = Color.parseColor("#555555");// 设置字体颜色

    float getTextSize() {
        return textSize;
    }

    void setTextSize(float textSize) {
        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setTextSize(textSize);
        }
        this.textSize = textSize;
    }

    int getTextColor() {
        return textColor;
    }

    protected void setTextColor(int textColor) {
        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setTextColor(textColor);
        }
        this.textColor = textColor;
    }

    // 构造方法
    public AutoVerticalScrollTextView(Context context) {
        this(context, null);
    }

    public AutoVerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        init();
    }

    // 初始化
    private void init() {
        setFactory(this);

        mInUp = createAnim(true, true);
        mOutUp = createAnim(false, true);

        setInAnimation(mInUp);//当View显示时动画资源ID
        setOutAnimation(mOutUp);//当View隐藏是动画资源ID。
    }

    // 创建Rotate3dAnimation
    private Rotate3dAnimation createAnim(boolean turnIn, boolean turnUp) {
        Rotate3dAnimation rotation = new Rotate3dAnimation(turnIn, turnUp);
        rotation.setDuration(1000);//执行动画的时间
        rotation.setFillAfter(false);//是否保持动画完毕之后的状态
        rotation.setInterpolator(new AccelerateInterpolator());//设置加速模式
        return rotation;
    }

    // 这里返回的TextView，就是我们看到的View,可以设置自己想要的效果
    @Override
    public View makeView() {
        TextView textView = new TextView(mContext);
        textView.setGravity(Gravity.START);
        textView.setTextSize(textSize);
        textView.setSingleLine(true);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(textColor);
        return textView;
    }

    // 定义动作，向上滚动翻页
    public void next() {
        //显示动画
        if (getInAnimation() != mInUp)
            setInAnimation(mInUp);
        //隐藏动画
        if (getOutAnimation() != mOutUp)
            setOutAnimation(mOutUp);
    }

    private class Rotate3dAnimation extends Animation {
        private float mCenterX;
        private float mCenterY;
        private final boolean mTurnIn;
        private final boolean mTurnUp;
        private Camera mCamera;// 用来保存初始Camera

        Rotate3dAnimation(boolean turnIn, boolean turnUp) {
            mTurnIn = turnIn;
            mTurnUp = turnUp;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            // 用来记录，初始Camera 高度 宽度
            mCamera = new Camera();
            mCenterY = getHeight();
            mCenterX = getWidth();
        }

        /**
         * interpolatedTime 0~1
         * t 转换
         */
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            // 标记是进入还是退出，-1进入，1推送
            final int derection = mTurnUp ? 1 : -1;
            // 获取转换矩阵
            final Matrix matrix = t.getMatrix();

            camera.save();
            if (mTurnIn) {
                // 进入时候，0 -> (derection * mCenterY * (interpolatedTime - 1.0f))[负值] -> 0
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime - 1.0f), 0.0f);
            } else {
                // 退出时候，0 -> (derection * mCenterY * (interpolatedTime - 1.0f))[正值] -> 0
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime), 0.0f);
            }
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }
}
