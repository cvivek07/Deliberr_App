package in.vivekchoudhary.com.deliberr_app.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.converter.GsonConverter;

/**
 * Created by cvivek on 10-09-2018.
 */

public class RestService {

    private retrofit.RestAdapter restAdapter;
    private ApiService apiService;

    public RestService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(Constants.BASE_API_URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        apiService = restAdapter.create(ApiService.class);
    }
}
