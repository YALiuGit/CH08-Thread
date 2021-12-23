package tw.tcnr03.m0802;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class M0802 extends AppCompatActivity implements View.OnClickListener {

    private TextView t001;
    private long startTime;
    private Handler handler = new Handler();  //繼承過來要給予一個記憶體位置
    private long spTime;
    private long minius;
    private long seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewComponent();
    }

    private void setupViewComponent() {
        t001=(TextView)findViewById(R.id.m0802_t001);
        //取得目前手機時間
        startTime=System.currentTimeMillis();
        //
        handler.postDelayed(updateTimer,100 ); //在多少時間後開始執行動作
//        t001.setText(startTime+"");
    }



    private Runnable updateTimer = new Runnable() {
        //Runnable為工單，描述要執行的工作內容
        @Override
        public void run() {
            spTime=System.currentTimeMillis()-startTime; //經過時間=現在時間-開始時間
            minius=(spTime/1000)/60; //目前已過分鐘數
            seconds=(spTime/1000)%60;//目前已過秒數

            t001.setText(String.format("%02d",minius)+":"+String.format("%02d", seconds));

            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTimer);
        this.finish();
    }
}