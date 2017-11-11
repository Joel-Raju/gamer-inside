package com.raju.joel.gamerinside.data.source.remote;


import com.raju.joel.gamerinside.BuildConfig;

/**
 * Created by Joel on 21-Sep-17.
 */

public class RemoteServiceUtils {

    public static final int STATUS_CODE_OK = 200;

    private static String getToken() {
        return BuildConfig.IGDB_API_KEY;
    }

    public static RemoteService getRemoteService() {
        return RemoteServiceGenerator.createRemoteService(RemoteService.class, getToken());
    }
}
