package in.vivekchoudhary.com.deliberr_app.model;

import android.content.Context;
import android.widget.Toast;

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

    public void getData(Context context, final DataSource.GetDataCallback callback) {
        remoteDataSource.getData(context, new DataSource.GetDataCallback() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
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
}
