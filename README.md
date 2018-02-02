# ZTextView
Android：不一样的TextView（一）水平滚动-跑马灯，以及自定义跑马灯TextView（MarqueeTextView）。Android：不一样的TextView（二）自定义垂直滚动TextView（AutoVerticalScrollTextView）。Android：不一样的TextView（三）SpannableStringTextView。使用工具Android Studio

引入Android Studio：

1、在build.gradle文件中添加以下代码：
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
```
dependencies {
	compile 'com.github.zrunker:ZTextView:v1.0.2'
}
```
2、在maven文件中添加以下代码：
```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
```
<dependency>
	<groupId>com.github.zrunker</groupId>
	<artifactId>ZTextView</artifactId>
	<version>v1.0.2</version>
</dependency>
```

用法：

Android：不一样的TextView（一）水平滚动-跑马灯，以及自定义跑马灯TextView（MarqueeTextView）:
只需要在XML布局文件中引入（android:singleLine="true"一定要有）：
```
<cc.ibooker.ztextviewlib.MarqueeTextView
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:text="@string/marquee_content" />
```
Android：不一样的TextView（二）自定义垂直滚动TextView（AutoVerticalScrollTextView）。
1、在XML布局文件中引入：
```
<cc.ibooker.ztextviewlib.AutoVerticalScrollTextView
	android:id="@+id/autoVerticalScrollTextView"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:background="@android:color/white"
	android:paddingBottom="10dp"
	android:paddingEnd="5dp"
	android:paddingLeft="5dp"
	android:paddingRight="5dp"
	android:paddingStart="5dp"
	android:paddingTop="10dp"
	android:text="@string/app_name" />
```
2、在java文件中进行调用（AutoVerticalScrollTextViewUtil）：
```
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
```
Android：不一样的TextView（三）SpannableStringTextView。
```
TextView testTv = (TextView) findViewById(R.id.tv_test);

// testTv.setText("测试修改颜色");
// SpannableStringTextViewUtil.updateForeColorSpan(testTv, 1, 3, "#40aff2");

// testTv.setText("测试添加颜色");
// SpannableStringTextViewUtil.addForeColorSpan(testTv, "TEST", 1, 3 , "#40aff2");

// testTv.setText("测试修改颜色");
// SpannableStringTextViewUtil.updateBackColorSpan(testTv, 1, 3, "#40aff2");

// testTv.setText("测试添加颜色");
// SpannableStringTextViewUtil.addBackColorSpan(testTv, "TEST", 1, 3 , "#40aff2");

// testTv.setText("测试添加文本大小");
// SpannableStringTextViewUtil.addFontSizeSpan(testTv, "TEST", 1, 3, 60);

// testTv.setText("测试添加超链接");
// SpannableStringTextViewUtil.addUrlSpan(testTv, "超链接", 0, 3, "http://www.baidu.com");

// testTv.setText("测试添加点击");
// SpannableStringTextViewUtil.addClickableSpan(testTv, "点击", 0, 2, new SpannableStringTextViewUtil.OnClickSpanListener() {
// @override
// public void onClickSpan() {
// Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
// }
// });

// testTv.setText("测试添加粗体+斜体");
// SpannableStringTextViewUtil.addStyleSpan(testTv, "TEST", 0, 3);

// testTv.setText("测试添加粗体");
// SpannableStringTextViewUtil.addStyleBoldSpan(testTv, "TEST", 0, 3);

// testTv.setText("测试添加斜体");
// SpannableStringTextViewUtil.addStyleItalicSpan(testTv, "TEST", 0, 3);

// testTv.setText("测试添加删除线");
// SpannableStringTextViewUtil.addStrikeSpan(testTv, "TEST", 0 , 3);

// testTv.setText("测试修改删除线");
// SpannableStringTextViewUtil.updateStrikeMySelfSpan(testTv, 0 , 3);

// testTv.setText("测试添加下划线");
// SpannableStringTextViewUtil.addUnderLineSpan(testTv, "下划线", 0, 3);

    testTv.setText("测试添加图片");
    SpannableStringTextViewUtil.addImageSpan(testTv, getResources().getDrawable(R.mipmap.ic_launcher)
```
