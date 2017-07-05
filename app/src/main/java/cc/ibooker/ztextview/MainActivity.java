package cc.ibooker.ztextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;

public class MainActivity extends AppCompatActivity implements AutoVerticalScrollTextViewUtil.OnMyClickListener {
    private ArrayList<String> list;
    private AutoVerticalScrollTextViewUtil aUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoVerticalScrollTextView textView = (AutoVerticalScrollTextView) findViewById(R.id.autoVerticalScrollTextView);

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add("测试垂直滚动" + i);

        // 初始化
        aUtil = new AutoVerticalScrollTextViewUtil(textView, list);
        aUtil.setDuration(5000)// 设置上下滚动事件间隔
                .start();
        // 点击事件监听
        aUtil.setOnMyClickListener(this);
    }

    // autoVerticalScrollTextView点击事件监听
    @Override
    public void onMyClickListener(int position, String title) {
        Toast.makeText(this, list.get(position) + " --- " + title, Toast.LENGTH_SHORT).show();
        if (aUtil.getIsRunning())
            // 停止滚动
            aUtil.stop();
        else
            // 开启滚动
            aUtil.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aUtil.stop();
    }
}
