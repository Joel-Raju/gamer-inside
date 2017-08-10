package com.raju.joel.gamerinside.data.source.remote;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class AuthenticationInterceptor implements Interceptor {

    private static final String REQUEST_HEADER_KEY_AUTH = "user-key";

    private String mAuthToken;

    public AuthenticationInterceptor(String mAuthToken) {
        this.mAuthToken = mAuthToken;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header(REQUEST_HEADER_KEY_AUTH, mAuthToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
