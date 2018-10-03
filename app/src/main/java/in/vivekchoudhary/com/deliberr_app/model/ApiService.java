package in.vivekchoudhary.com.deliberr_app.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cvivek on 10-09-2018.
 */

public interface ApiService {

    @GET("launches")
    Call<JsonArray> getAllLaunches();

    @GET("rockets/{rocket_id}")
    Call<JsonObject> getOneRocket(@Path("rocket_id") String rocket_id);

}
