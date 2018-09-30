package in.vivekchoudhary.com.deliberr_app.model;

import android.content.Context;

import com.google.gson.JsonObject;


import in.vivekchoudhary.com.deliberr_app.util.MainUiThread;
import in.vivekchoudhary.com.deliberr_app.util.ThreadExecutor;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cvivek on 10-09-2018.
 */

public class RemoteDataSource extends DataSource {
    private static RemoteDataSource remoteDataSource;
    private ApiService apiService;
    private RestService restService;

    private RemoteDataSource(MainUiThread mainUiThread,
                             ThreadExecutor threadExecutor, ApiService apiService) {
        super(mainUiThread, threadExecutor);
        this.apiService = apiService;
        restService = new RestService();
    }

    public static synchronized RemoteDataSource getInstance(MainUiThread mainUiThread,
                                                            ThreadExecutor threadExecutor,
                                                            ApiService apiService) {
        if (remoteDataSource == null) {

            remoteDataSource = new RemoteDataSource(mainUiThread, threadExecutor, apiService);
        }
        return remoteDataSource;
    }

    @Override
    public void getData(Context context, final GetDataCallback callback) {
        retrofit2.Call<JsonObject> call = apiService.getData();
        call.enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
