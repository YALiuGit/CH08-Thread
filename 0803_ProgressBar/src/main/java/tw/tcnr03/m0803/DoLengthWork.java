package tw.tcnr03.m0803;

import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import java.util.Calendar;


public class DoLengthWork extends Thread {
    private Handler mHandler;
    private ProgressBar mProBar;
    String TAG = "03ya=>";
    private int max=100;

    public void run() {
        Calendar begin = Calendar.getInstance();
        do {
            Calendar now = Calendar.getInstance();
            final int iDiffSec = 60 * (now.get(Calendar.MINUTE)
                    - begin.get(Calendar.MINUTE)) + now.get(Calendar.SECOND)
                    - begin.get(Calendar.SECOND);
            if (iDiffSec * 10 > max) {
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setProgress(max);
                    }
                });
                //若跑完進度不想離開程式則mark此行
                System.exit(0);
                break;
            }
            mHandler.post(new Runnable() {
                public void run() {
                    mProBar.setProgress(iDiffSec * 10);
                }
            });
            if (iDiffSec * 15 < max)
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(iDiffSec * 15);
                    }
                });
            else
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(max);
                    }
                });
        } while (true);
        Log.d(TAG,"end");
    }
    //---------------------------------
    void setProgressBar(ProgressBar proBar) {
        mProBar = proBar;
    }
    //------------------------------------
    void setHandler(Handler h) {
        mHandler = h;
    }
}