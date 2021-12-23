package tw.tcnr03.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class M0802a extends AppCompatActivity implements View.OnClickListener {


    private TextView ans001;
    private DatePicker mdate;
    private TimePicker mtime;
    private Button b001;
    private TextView timer;
    private TextView txt001;
    private MediaPlayer startmusic;
    private int years01;
    private int months01;
    private int dates01;
    private int hours01;
    private int minius01;
    private Calendar cg;
    private long endTime;
    private Handler handler=new Handler();
    private long spTime;
    private long minius;
    private long seconds;
    private long hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802a);
        setupViewComponent();
    }

    private void setupViewComponent() {
        ans001=findViewById(R.id.m0802a_ans001);
        mdate=findViewById(R.id.m0802a_date01);
        mtime=findViewById(R.id.m0802a_time01);
        b001=findViewById(R.id.m0802a_b001);
        txt001=findViewById(R.id.m0802a_t001);
        timer=findViewById(R.id.m0802a_timer);

        startmusic= MediaPlayer.create(M0802a.this, R.raw.s01);

        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0802a_ans001);
        years01 = mdate.getYear();//取得畫面的"年"
        months01 = mdate.getMonth();//取得畫面的"月"
        dates01 = mdate.getDayOfMonth();//取得畫面的"日"
        hours01 = mtime.getHour();// 取得畫面的"小時"
        minius01 = mtime.getMinute();// 取得畫面的"分鐘"

        // 顯示選擇的日期和時間
        ans001.setText(s +
                years01 + getString(R.string.n_yy) +
                (months01 + 1) + getString(R.string.n_mm)     +
                dates01 + getString(R.string.m_dd) +
                hours01 + getString(R.string.d_hh) +
                minius01      + getString(R.string.d_mm));
        //--------------------------------------
        cg= Calendar.getInstance(); //設定日曆物件
        cg.set(years01, months01, dates01, hours01, minius01); //將日期及時間設定進去物件
        endTime=cg.getTimeInMillis(); //取得時間毫秒資料
        //設定開始Delay的時間
        handler.postDelayed(updateTimer, 100);
    }


    private  Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            spTime=endTime-System.currentTimeMillis(); //經過時間=現在時間-開始時間
            hours=(spTime/1000)/60/60; //目前已過小時數
            minius=(spTime/1000)/60; //目前已過分鐘數
            seconds=(spTime/1000)%60;//目前已過秒數

            if(spTime < 0 || hours > 99){
                Toast.makeText(getApplicationContext(), R.string.m0802a_err, Toast.LENGTH_LONG).show();
                timer.setText("88:88:88");
                txt001.setText(getString(R.string.m0802a_ans001));
                handler.removeCallbacks(updateTimer); //移除任務
            } else {
                txt001.setText(getString(R.string.m0802a_alerm));
                music_set();
                timer.setText(String.format("%02d",hours)+":"+String.format("%02d",minius)+":"+String.format("%02d", seconds));
                handler.postDelayed(this, 1000);

                if(hours == 0 && minius == 0 && seconds == 0){
                    startmusic.start();
                    txt001.setText(R.string.m0802a_play);
                    handler.removeCallbacks(updateTimer); //移除任務
                }
            }

        }
    };

    public void music_set() {
//        中斷播放中音樂
        if(startmusic!=null && startmusic.isPlaying())
            startmusic.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}