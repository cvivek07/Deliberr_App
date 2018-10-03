package in.vivekchoudhary.com.deliberr_app.model;

import android.content.Context;

import com.google.gson.JsonArray;
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
    public void getAllLaunches(Context context, final GetAllLaunchesCallback callback) {
        Call<JsonArray> call = apiService.getAllLaunches();
        call.enqueue(new retrofit2.Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getOneRocket(Context context, final String rocket_id, final GetOneRocketCallback callback) {
        Call<JsonObject> call = apiService.getOneRocket(rocket_id);
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
