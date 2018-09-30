package in.vivekchoudhary.com.deliberr_app.model;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cvivek on 10-09-2018.
 */

public interface ApiService {

    /*@GET("5b700cff2e00005c009365cf")
    Call<JsonObject> getData();*/

    @GET("latest")
    Call<JsonObject> getData();


}
