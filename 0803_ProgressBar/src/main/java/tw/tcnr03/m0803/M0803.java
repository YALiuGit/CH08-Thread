package tw.tcnr03.m0803;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;

public class M0803 extends AppCompatActivity {

    private ProgressBar proBar;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0803);
        setupViewComponent();
    }

    private void setupViewComponent() {
        proBar=(ProgressBar)findViewById(R.id.m0803_p001) ;
        DoLengthWork work = new DoLengthWork();
        work.setHandler(mHandler);
        work.setProgressBar(proBar);
        work.start();
        work.checkAccess();

        //創建一個 SpannableString物件
//        SpannableString sp1 = new SpannableString(getString(R.string.m0803_t001));
        //設置超連結
//        sp.setSpan(new URLSpan("http://www.google.com.tw"), 5, 11,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //設置高亮樣式一
//        sp1.setSpan(new BackgroundColorSpan(Color.RED), 15 ,20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //設置高亮樣式二
//        sp1.setSpan(new ForegroundColorSpan(Color.YELLOW),21,23,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //設置斜體
//        sp1.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 29, 33, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //SpannableString物件設置給TextView
//        t001.setText(sp1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(null);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //禁用返回
    }
}