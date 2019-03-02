package vn.tiki.test_tiki.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhat on 3/2/19
 */
public final class RestClient {

    private static RestClient instance;
    private static Retrofit retrofit;
    private Context mContext;

    private static final String BASE_URL = "https://raw.githubusercontent.com/tikivn/android-home-test/";

    public static RestClient getInstance(Context context, boolean isRefresh) {
        if (instance == null || isRefresh)
            instance = new RestClient(context);
        return instance;
    }

    public RestClient(Context pContext) {
        mContext = pContext;
        // Gson
        Gson gson = new GsonBuilder()
                .serializeNulls().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        // Logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // init retrofit with token
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .addHeader("token", "")
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ManagerService getManagerService() {
        return retrofit.create(ManagerService.class);
    }
}
