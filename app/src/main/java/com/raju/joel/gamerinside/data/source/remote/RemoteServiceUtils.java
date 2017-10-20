package com.raju.joel.gamerinside.data.source.remote;


/**
 * Created by Joel on 21-Sep-17.
 */

public class RemoteServiceUtils {

    private static String getToken() {
        return "d44cep123tqa2bni6j2fd39ea4e7ll";
    }

    public static RemoteService getRemoteService() {
        return RemoteServiceGenerator.createRemoteService(RemoteService.class, getToken());
    }
}
