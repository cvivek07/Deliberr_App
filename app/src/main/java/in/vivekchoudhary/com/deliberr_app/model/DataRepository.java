package in.vivekchoudhary.com.deliberr_app.model;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import in.vivekchoudhary.com.deliberr_app.util.NetworkHelper;


/**
 * Created by cvivek on 10-09-2018.
 */

public class DataRepository {
    private DataSource remoteDataSource;
    private NetworkHelper networkHelper;

    public static final String TAG = DataRepository.class.getSimpleName();

    private static DataRepository dataRepository;

    public DataRepository(DataSource remoteDataSource,
                          NetworkHelper networkHelper) {
        this.remoteDataSource = remoteDataSource;
        this.networkHelper = networkHelper;
    }

    public static synchronized DataRepository getInstance(DataSource remoteDataSource,
                                                          NetworkHelper networkHelper) {
        if (dataRepository == null) {
            dataRepository = new DataRepository(remoteDataSource, networkHelper);
        }
        return dataRepository;
    }

    public void getAllLaunches(Context context, final DataSource.GetAllLaunchesCallback callback) {
        remoteDataSource.getAllLaunches(context, new DataSource.GetAllLaunchesCallback() {
            @Override
            public void onSuccess(JsonArray jsonObject) {
                callback.onSuccess(jsonObject);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onNetworkFailure() {

            }
        });
    }

    public void getOneRocket(Context context, String rocket_id, final DataSource.GetOneRocketCallback callback) {
        remoteDataSource.getOneRocket(context, rocket_id, new DataSource.GetOneRocketCallback() {
            @Override
            public void onSuccess(JsonObject jsonData) {
                callback.onSuccess(jsonData);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            @Override
            public void onNetworkFailure() {
                callback.onNetworkFailure();
            }
        });
    }
}
