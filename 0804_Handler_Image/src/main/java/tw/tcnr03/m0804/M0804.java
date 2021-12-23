package tw.tcnr03.m0804;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
@SuppressWarnings("deprecation")

public class M0804 extends AppCompatActivity {

    private ImageView im001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0804);
        setupViewComponent();
    }

    private void setupViewComponent() {
        im001=(ImageView)findViewById(R.id.m0804_im001);
        UserThread myThread = new UserThread(this);
        myThread.start();
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 0:
                    im001.setImageResource(R.drawable.flow01b);
                    break;
                case 1:
                    im001.setImageResource(R.drawable.flow02b);
                    break;
                case 2:
                    im001.setImageResource(R.drawable.flow03b);
                    break;
                case 3:
                    im001.setImageResource(R.drawable.flow04b);
                    break;
            }
        }
    };
}