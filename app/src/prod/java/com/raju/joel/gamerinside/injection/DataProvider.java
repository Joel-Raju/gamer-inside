package com.raju.joel.gamerinside.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.source.GamesRepository;
import com.raju.joel.gamerinside.data.source.NewsRepository;
import com.raju.joel.gamerinside.data.source.remote.GamesRemoteDataSource;
import com.raju.joel.gamerinside.data.source.remote.NewsRemoteDataSource;

/**
 * Created by Joel on 11-Sep-17.
 */

public class DataProvider {

    public static NewsRepository provideNewsRepository(@NonNull Context context) {
        return NewsRepository.getInstance(NewsRemoteDataSource.getInstance(context));
    }

    public static GamesRepository provideGamesRepository(@NonNull Context context) {
        return GamesRepository.getInstance(GamesRemoteDataSource.getInstance(context));
    }

}
