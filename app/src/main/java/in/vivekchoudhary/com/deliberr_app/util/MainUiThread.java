package in.vivekchoudhary.com.deliberr_app.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by cvivek on 10-09-2018.
 */

public class MainUiThread {

    private static MainUiThread mainUiThread;

    private Handler handler;

    private MainUiThread() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static synchronized MainUiThread getInstance() {
        if (mainUiThread == null) {
            mainUiThread = new MainUiThread();
        }
        return mainUiThread;
    }

    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
