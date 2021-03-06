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
    private Button b002;
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
    private int years02;
    private int month02;
    private int days02;
    private int hours02;
    private int min02;

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
        b002=findViewById(R.id.m0802a_b002);
        txt001=findViewById(R.id.m0802a_t001);
        timer=findViewById(R.id.m0802a_timer);

        startmusic= MediaPlayer.create(M0802a.this, R.raw.s01);

        b001.setOnClickListener(this);
        b002.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m0802a_b001:
                b001.setVisibility(View.INVISIBLE);
                b002.setVisibility(View.VISIBLE);
                String s = getString(R.string.m0802a_ans001);
                years01 = mdate.getYear();//???????????????"???"
                months01 = mdate.getMonth();//???????????????"???"
                dates01 = mdate.getDayOfMonth();//???????????????"???"
                hours01 = mtime.getHour();// ???????????????"??????"
                minius01 = mtime.getMinute();// ???????????????"??????"

                // ??????????????????????????????
                ans001.setText(s +
                        years01 + getString(R.string.n_yy) +
                        (months01 + 1) + getString(R.string.n_mm)     +
                        dates01 + getString(R.string.m_dd) +
                        hours01 + getString(R.string.d_hh) +
                        minius01      + getString(R.string.d_mm));
                //--------------------------------------
                cg= Calendar.getInstance(); //??????????????????
                cg.set(years01, months01, dates01, hours01, minius01); //????????????????????????????????????
                endTime=cg.getTimeInMillis(); //????????????????????????
                //????????????Delay?????????
                handler.postDelayed(updateTimer, 100);
                break;

            case R.id.m0802a_b002:
                b001.setVisibility(View.VISIBLE);
                b002.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), R.string.m0802a_b002, Toast.LENGTH_LONG).show();
                music_set();
                timer.setText("88:88:88");
                txt001.setText(getString(R.string.m0802a_ans001));
                setnowtime();
                handler.removeCallbacks(updateTimer); //????????????
                break;
        }


    }

    private void setnowtime() {
        Calendar c = Calendar.getInstance();
        years02 = c.get(Calendar.YEAR);
        month02 = c.get(Calendar.MONTH);
        days02 = c.get(Calendar.DAY_OF_MONTH);
        hours02 = c.get(Calendar.HOUR_OF_DAY);
        min02 = c.get(Calendar.MINUTE);
        //-----------------
        mdate.updateDate(years02, month02, days02);
        mtime.setHour(hours02);
        mtime.setMinute(min02);
    }


    private  Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            spTime=endTime-System.currentTimeMillis(); //????????????=????????????-????????????
            hours=(spTime/1000)/60/60; //?????????????????????
            minius=(spTime/1000)/60; //?????????????????????
            seconds=(spTime/1000)%60;//??????????????????

            if(spTime < 0 || hours > 99){
                Toast.makeText(getApplicationContext(), R.string.m0802a_err, Toast.LENGTH_LONG).show();
                timer.setText("88:88:88");
                txt001.setText(getString(R.string.m0802a_ans001));
                handler.removeCallbacks(updateTimer); //????????????
            } else {
                txt001.setText(getString(R.string.m0802a_alerm));
                music_set();
                timer.setText(String.format("%02d",hours)+":"+String.format("%02d",minius)+":"+String.format("%02d", seconds));
                handler.postDelayed(this, 1000);

                if(hours == 0 && minius == 0 && seconds == 0){
                    startmusic.start();
                    txt001.setText(R.string.m0802a_play);
                    handler.removeCallbacks(updateTimer); //????????????
                }
            }

        }
    };

    public void music_set() {
//        ?????????????????????
        if(startmusic!=null && startmusic.isPlaying())
            startmusic.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}