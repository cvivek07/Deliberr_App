package in.vivekchoudhary.com.deliberr_app.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import in.vivekchoudhary.com.deliberr_app.model.ApiService;
import in.vivekchoudhary.com.deliberr_app.model.Constants;
import in.vivekchoudhary.com.deliberr_app.model.DataRepository;
import in.vivekchoudhary.com.deliberr_app.model.RemoteDataSource;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cvivek on 10-09-2018.
 */

public class RetrofitBuilder {
    public static DataRepository provideDataRepository(MainUiThread mainUiThread,
                                                       ThreadExecutor threadExecutor) {


        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl httpUrl = chain.request().url().newBuilder()
                        .build();
                Request newRequest = chain.request().newBuilder().url(httpUrl).build();
                return chain.proceed(newRequest);
            }
        };

        /*HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);*/
        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.MINUTES)
                        .readTimeout(10, TimeUnit.MINUTES)
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        return DataRepository.getInstance(
                RemoteDataSource.getInstance(mainUiThread, threadExecutor, apiService),
                NetworkHelper.getInstance());
    }
}
