package tw.tcnr03.m0801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class M0801 extends AppCompatActivity implements View.OnClickListener {


    private TextView t001;
    private DatePicker mdate;
    private TimePicker mtime;
    private Button b001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0801);
        setupViewComponent();
    }

    private void setupViewComponent() {
        t001=findViewById(R.id.m0801_t001);
        mdate=findViewById(R.id.m0801_date01);
        mtime=findViewById(R.id.m0801_time01);
        b001=findViewById(R.id.m0801_b001);
        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0801_t001);
        String ss= Convert12to24(mtime.getHour()+":"+mtime.getMinute()+":00");
        t001.setText(s + "\n" +
                mdate.getYear() + getString(R.string.n_yy) +
                (mdate.getMonth()+1) + getString(R.string.n_mm) +
                mdate.getDayOfMonth() +getString(R.string.m_dd) + "\n" +
                ss);
    }

    private String Convert12to24(String s) {
        String cvt="";
        try {
            SimpleDateFormat inputTime = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm:a");

            Date date=inputTime.parse(s);
            cvt=outputTime.format(date);

        }catch ( ParseException e){
            e.printStackTrace();
        }
        return cvt;
    }
}