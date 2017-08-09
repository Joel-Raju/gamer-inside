package com.raju.joel.gamerinside.data.source.remote;

import retrofit2.Retrofit;

public class RemoteClient {

    private static Retrofit mRetrofit = null;

    private static final String BASE_URL = "https://api-2445582011268.apicast.io/";

    public static Retrofit getInstance() {

        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }

        return mRetrofit;
    }
}
