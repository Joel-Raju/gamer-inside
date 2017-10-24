package com.raju.joel.gamerinside.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;



public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int layoutId) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layoutId, fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }

    public static void detachFragmentFromActivity(@NonNull FragmentManager fragmentManager,
                                                  @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.detach(fragment);
        transaction.commit();
    }

    public static void attachFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.attach(fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()));
        transaction.commit();
    }

    public static boolean isFragmentPreviouslyAddedToActivity(@NonNull FragmentManager fragmentManager,
                                                       @NonNull Fragment fragment) {
        return (fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) != null);
    }
}
