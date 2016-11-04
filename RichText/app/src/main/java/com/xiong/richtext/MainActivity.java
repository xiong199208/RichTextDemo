package com.xiong.richtext;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.text);

       /* String html="<font color='red'>test1</font> <br>";
        html+="<font color='#0000FF'> <big> <i> test2 </i> </big> <font>";
        html+="<font color='@"+android.R.color.black+"'> <tt> <b> <big> <u> test3 </u> </big>";
*/
        String content = r("红色") + ", 警戒, "+g("绿巨人")+", <big><font color='#FFC0CB'>粉色</font></big>, " +
                b("蓝色");
        //将带标签的String类型解析成android下可识别HTML标签的Spanned类型
        //String和Spanned都实现了CharSequence接口，所以TextView可以解读Spanned。
        Spanned spanned = Html.fromHtml(content);
        text.setText(spanned);

        //----------------------------------------------------------表情

        //px转dip
        int bound = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        TextView tv_emoji = (TextView) findViewById(R.id.tv_emoji);
        String text1 = "滑稽树下滑稽果，滑稽树下你和我";

        //将字符串转化成符合文本，可设置各种样式
        SpannableString spannable = new SpannableString(text1);
        // 获取图片
        Drawable drawable = getResources().getDrawable(R.drawable.joy);
        // 设置四边宽高
        drawable.setBounds(0, 0, bound, bound);
        // 构建图片区域，格式为文字在上
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        // 替换指定位置的内容
        spannable.setSpan(imageSpan, 7, 8 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_emoji.setText(spannable);

        //---------------------------------------------超链接
        String linkText = "百度,电话,@大保健";
        TextView tv_link = (TextView) findViewById(R.id.tv_link);

        // 包含链接时, 设置此属性
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());

        SpannableString link = new SpannableString(linkText);

        //跳转到网页
        link.setSpan(new URLSpan("https://m.baidu.com"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //隐式意图
        link.setSpan(new URLSpan("tel:110"), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //自定义超链接
        link.setSpan(new MyURLSpan("来人！扶我去大保健！"), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_link.setText(link);

        //-------------------------------------------------富文本的替换规则
        EditText et_spaned = (EditText) findViewById(R.id.et_spaned);
        String text2 = "_前包后不包_后包前不包_前后都包括_前后都不包_";

        SpannableString flag = new SpannableString(text2);

        flag.setSpan(new ForegroundColorSpan(Color.RED), 1, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        flag.setSpan(new ForegroundColorSpan(Color.GREEN), 7, 12, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        flag.setSpan(new ForegroundColorSpan(Color.BLUE), 13, 18, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        flag.setSpan(new ForegroundColorSpan(Color.MAGENTA), 19, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        et_spaned.setText(flag);

    }

    @NonNull
    public static String r(String str) { // red
        return String.format("<font color='red'>%s</font>", str);
    }
    @NonNull
    public static String g(String str) { // green
        return String.format("<font color='#00FF00'>%s</font>", str);
    }
    @NonNull
    public static String b(String str) { // blue
        return String.format("<font color='#0000FF'>%s</font>", str);
    }

    /**
     * 自定义超链接
     */
    class MyURLSpan extends URLSpan{

        public MyURLSpan(String url) {
            super(url);
        }
        @Override
        public void onClick(View widget) {
            String url = getURL();
            Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
        }
    }

}
