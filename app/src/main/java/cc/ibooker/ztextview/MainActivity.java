package cc.ibooker.ztextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;
import cc.ibooker.ztextviewlib.SpannableStringTextViewUtil;

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


        TextView testTv = (TextView) findViewById(R.id.tv_test);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.updateForeColorSpan(testTv, 1, 3, "#40aff2");

//        testTv.setText("test");
//        SpannableStringTextViewUtil.addForeColorSpan(testTv, "测试修改颜色为ABC", 1, 3 , "#40aff2");

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.updateBackColorSpan(testTv, 1, 3, "#40aff2");

//        testTv.setText("test");
//        SpannableStringTextViewUtil.addBackColorSpan(testTv, "测试修改颜色为ABC", 1, 3 , "#40aff2");

//        SpannableStringTextViewUtil.addFontSizeSpan(testTv, "测试修改颜色为ABC", 1, 3, 60);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addUrlSpan(testTv, "超链接", 0, 3, "http://www.baidu.com");

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addClickableSpan(testTv, "点击", 0, 2, new SpannableStringTextViewUtil.OnClickSpanListener() {
//            @Override
//            public void onClickSpan() {
//                Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
//            }
//        });

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addStyleSpan(testTv, "123456", 0, 3);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addStyleBoldSpan(testTv, "123456", 0, 3);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addStyleItalicSpan(testTv, "123456", 0, 3);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addStrikeSpan(testTv, "123456", 0 , 3);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.updateStrikeMySelfSpan(testTv, 0 , 3);

//        testTv.setText("测试修改颜色为ABC");
//        SpannableStringTextViewUtil.addUnderLineSpan(testTv, "下划线", 0, 3);

        testTv.setText("测试修改颜色为ABC");
        SpannableStringTextViewUtil.addImageSpan(testTv, getResources().getDrawable(R.mipmap.ic_launcher));
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
