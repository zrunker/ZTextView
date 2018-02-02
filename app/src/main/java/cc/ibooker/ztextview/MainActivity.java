package cc.ibooker.ztextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;
import cc.ibooker.ztextviewlib.SpannableStringTextViewUtil;

public class MainActivity extends AppCompatActivity implements AutoVerticalScrollTextViewUtil.OnMyClickListener {
    private ArrayList<CharSequence> list;
    private AutoVerticalScrollTextViewUtil aUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoVerticalScrollTextView textView = (AutoVerticalScrollTextView) findViewById(R.id.autoVerticalScrollTextView);

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 2 || i == 4) {
                list.add(Html.fromHtml("<font color='#FF7198'>" + "测试垂直滚动" + "</font>" + i + "特殊"));
            } else {
                list.add("测试垂直滚动" + i);
            }
        }

        // 初始化
        aUtil = new AutoVerticalScrollTextViewUtil(textView, list);
        aUtil.setDuration(5000)// 设置上下滚动事件间隔
                .start();
        // 点击事件监听
        aUtil.setOnMyClickListener(this);


        TextView testTv = (TextView) findViewById(R.id.tv_test);

//        testTv.setText("测试修改颜色");
//        SpannableStringTextViewUtil.updateForeColorSpan(testTv, 1, 3, "#40aff2");

//        testTv.setText("测试添加颜色");
//        SpannableStringTextViewUtil.addForeColorSpan(testTv, "TEST", 1, 3 , "#40aff2");

//        testTv.setText("测试修改颜色");
//        SpannableStringTextViewUtil.updateBackColorSpan(testTv, 1, 3, "#40aff2");

//        testTv.setText("测试添加颜色");
//        SpannableStringTextViewUtil.addBackColorSpan(testTv, "TEST", 1, 3 , "#40aff2");

//        testTv.setText("测试添加文本大小");
//        SpannableStringTextViewUtil.addFontSizeSpan(testTv, "TEST", 1, 3, 60);

//        testTv.setText("测试添加超链接");
//        SpannableStringTextViewUtil.addUrlSpan(testTv, "超链接", 0, 3, "http://www.baidu.com");

//        testTv.setText("测试添加点击");
//        SpannableStringTextViewUtil.addClickableSpan(testTv, "点击", 0, 2, new SpannableStringTextViewUtil.OnClickSpanListener() {
//            @Override
//            public void onClickSpan() {
//                Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
//            }
//        });

//        testTv.setText("测试添加粗体+斜体");
//        SpannableStringTextViewUtil.addStyleSpan(testTv, "TEST", 0, 3);

//        testTv.setText("测试添加粗体");
//        SpannableStringTextViewUtil.addStyleBoldSpan(testTv, "TEST", 0, 3);

//        testTv.setText("测试添加斜体");
//        SpannableStringTextViewUtil.addStyleItalicSpan(testTv, "TEST", 0, 3);

//        testTv.setText("测试添加删除线");
//        SpannableStringTextViewUtil.addStrikeSpan(testTv, "TEST", 0 , 3);

//        testTv.setText("测试修改删除线");
//        SpannableStringTextViewUtil.updateStrikeMySelfSpan(testTv, 0 , 3);

//        testTv.setText("测试添加下划线");
//        SpannableStringTextViewUtil.addUnderLineSpan(testTv, "下划线", 0, 3);

        testTv.setText("测试添加图片");
        SpannableStringTextViewUtil.addImageSpan(testTv, getResources().getDrawable(R.mipmap.ic_launcher));
    }

    // autoVerticalScrollTextView点击事件监听
    @Override
    public void onMyClickListener(int position, CharSequence title) {
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
