package cc.ibooker.ztextviewlib;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来管理AutoVerticalScrollTextView
 * Created by 邹峰立 on 2017/6/30.
 */
public class AutoVerticalScrollTextViewUtil {
    private static final int MESSAGE_CODE = 200;

    private long duration = 1000;// 停顿时间毫秒（ms）-默认1s

    private MyHandler handler = new MyHandler(this);

    private AutoVerticalScrollTextView textView;// 目标TextView

    private ExecutorService executorService;// 线程池

    private boolean isRunning;// 标记是否为滚动状态

    private int number = 0;// 用来记录滚动次数
    private int currentPos = 0;// 标记当前显示哪一项

    private ArrayList<CharSequence> mDatas = new ArrayList<>();// 需要轮播的数据源

    private CharSequence title;// 需要改变TextView上面的文本内容

    // 构造方法
    public AutoVerticalScrollTextViewUtil(final AutoVerticalScrollTextView textView, ArrayList<CharSequence> datas) {
        this.mDatas = datas;
        this.textView = textView;
        // 控件点击事件监听
        this.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZTextViewClickUtil.isFastClick())
                    return;
                if (onMyClickListener != null)
                    onMyClickListener.onMyClickListener(currentPos, title);
            }
        });
    }

    // 设置停顿时间ms
    public AutoVerticalScrollTextViewUtil setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    // 开始轮播
    public void start() {
        isRunning = true;
        // 开启线程
        startThread();
    }

    // 关闭轮播
    public void stop() {
        isRunning = false;
        if (executorService != null)
            executorService.shutdownNow();
    }

    // 获取当前运行状态
    public boolean getIsRunning() {
        return isRunning;
    }

    // 设置字体大小
    public AutoVerticalScrollTextViewUtil setTextSize(float textSize) {
        textView.setTextSize(textSize);
        return this;
    }

    // 设置字体颜色
    public AutoVerticalScrollTextViewUtil setTextColor(@ColorInt int textColor) {
        textView.setTextColor(textColor);
        return this;
    }

    // 开启线程
    private void startThread() {
        // 定义一个执行线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mDatas == null || mDatas.size() <= 0) {
                        // 当数据源为空的时候，什么都不做
                        isRunning = false;
                    } else {
                        while (isRunning) {
                            // 获取要显示的数据
                            currentPos = number % mDatas.size();
                            title = mDatas.get(currentPos);
                            number++;
                            // 改变文本显示
                            handler.sendEmptyMessage(MESSAGE_CODE);
                            // 停顿
                            Thread.sleep(duration);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 线程池进行管理
        if (executorService == null || executorService.isShutdown())
            executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.execute(thread);
    }

    // 定义Handler修改主线程
    private static class MyHandler extends Handler {
        // 软引用
        private final WeakReference<AutoVerticalScrollTextViewUtil> mUtil;

        MyHandler(AutoVerticalScrollTextViewUtil util) {
            mUtil = new WeakReference<>(util);
        }

        @Override
        public void handleMessage(Message msg) {
            AutoVerticalScrollTextViewUtil currentUtil = mUtil.get();
            if (msg.what == MESSAGE_CODE) {
                currentUtil.textView.next();
                if (!TextUtils.isEmpty(currentUtil.title))
                    currentUtil.textView.setText(currentUtil.title);
            }
        }
    }

    // 点击事件监听
    public interface OnMyClickListener {
        void onMyClickListener(int position, CharSequence title);
    }

    private OnMyClickListener onMyClickListener;

    public void setOnMyClickListener(OnMyClickListener onMyClickListener) {
        this.onMyClickListener = onMyClickListener;
    }
}