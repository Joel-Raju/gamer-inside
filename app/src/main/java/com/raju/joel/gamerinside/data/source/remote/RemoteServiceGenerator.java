package com.raju.joel.gamerinside.data.source.remote;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceGenerator {

    private static final String BASE_URL = "https://api-2445582011268.apicast.io/";

    private static Retrofit.Builder mBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    private static HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit mRetrofit = mBuilder.build();

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();

    public static <S> S createRemoteService(Class<S> remoteServiceClass) {
        return createRemoteService(remoteServiceClass, null);
    }

    public static <S> S createRemoteService(Class<S> remoteServiceClass, String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(authToken);

            if (!mHttpClient.interceptors().contains(authInterceptor)) {
                mHttpClient.addInterceptor(authInterceptor);
            }

            if (!mHttpClient.interceptors().contains(mLoggingInterceptor)) {
                mHttpClient.addInterceptor(mLoggingInterceptor);
            }

            mBuilder.client(mHttpClient.build());
            mRetrofit = mBuilder.build();
        }

        return mRetrofit.create(remoteServiceClass);
    }


}
