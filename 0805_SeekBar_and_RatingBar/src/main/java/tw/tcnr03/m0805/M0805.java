package tw.tcnr03.m0805;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class M0805 extends AppCompatActivity  {

    private SeekBar skb01;
    private TextView t001;
    private RatingBar rat01;
    private TextView rat01a;
    private TextView rat01b;
    private Button b001;
    private Button b002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0805);
        setupViewComponent();
    }

    private void setupViewComponent() {
        skb01=(SeekBar)findViewById(R.id.m0805_SeekBar);
        t001 = (TextView) findViewById(R.id.m0805_t001);
        rat01=(RatingBar)findViewById(R.id.m0805_RatBar);
        rat01a=(TextView)findViewById(R.id.m0805_Rat01);
        rat01b=(TextView)findViewById(R.id.m0805_Rat02);
        b001=(Button)findViewById(R.id.m0805_b001);
        b002=(Button)findViewById(R.id.m0805_b002);
        skb01.setOnSeekBarChangeListener(skbON);
        rat01.setOnRatingBarChangeListener(ratON);
    }
    SeekBar.OnSeekBarChangeListener skbON = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String s1= getString(R.string.m0805_SeekBar);
            t001.setText(s1+Integer.toString(progress));

            b001.getBackground().setAlpha(progress*255/100); //100 layout max
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    RatingBar.OnRatingBarChangeListener ratON = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String s2= getString(R.string.m0805_RatBar1);
            rat01a.setText(s2+Float.toString(rating));
            String s3 = getString(R.string.m0805_RatBar2);
            rat01b.setText(s3+Integer.toString(rat01.getProgress()));
            b002.getBackground().setAlpha(rat01.getProgress()*255/10); //共5顆星，每次只增減半顆星



        }
    };


}