package in.vivekchoudhary.com.deliberr_app.model;

import android.content.Context;

import com.google.gson.JsonObject;



import in.vivekchoudhary.com.deliberr_app.util.MainUiThread;
import in.vivekchoudhary.com.deliberr_app.util.ThreadExecutor;

/**
 * Created by cvivek on 10-09-2018.
 */

public abstract class DataSource {

    protected MainUiThread mainUiThread;
    protected ThreadExecutor threadExecutor;

    public DataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        this.mainUiThread = mainUiThread;
        this.threadExecutor = threadExecutor;
    }

    public interface GetDataCallback {
        void onSuccess(JsonObject jsonData);

        void onFailure(Throwable throwable);

        void onNetworkFailure();
    }

    public abstract void getData(Context context, GetDataCallback callback);
}
