package com.raju.joel.gamerinside.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.source.GamesDataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Joel on 11-Sep-17.
 */

public class GamesRemoteDataSource implements GamesDataSource {

    private static GamesRemoteDataSource INSTANCE = null;

    private static RemoteService mService;

    private static String mPopularGamesPaginationURL = "";

    private static int mPopularGamesResultCount = 0;

    private static final String RESPONSE_HEADER_PAGINATION_URL = "x-next-page";

    private static final String RESPONSE_HEADER_RESULT_COUNT = "x-count";



    public static GamesRemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GamesRemoteDataSource(context);
            mService = RemoteServiceUtils.getRemoteService();
        }
        return INSTANCE;
    }

    private GamesRemoteDataSource(@NonNull Context context) {

    }

    @Override
    public void getPopularGames(@NonNull final LoadGamesCallback callback) {
        mService.getPopularGames().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //TODO: check 404, 403 etc
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onGamesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getMostAnticipatedGames(@NonNull final LoadGamesCallback callback) {
        Date date = new Date();
        String dateLowerBound = new SimpleDateFormat("yyyy-MM-dd").format(date);
        int nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1 ;
        String dateUpperBound = String.valueOf(nextYear) + "-12-31";
        String orderQuery = "hypes:desc";

        mService.getMostAnticipatedGames(dateLowerBound, dateUpperBound, orderQuery).enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //TODO: check 404, 403 etc
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onGamesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUpcomingGames(@NonNull final LoadGamesCallback callback) {
        Date date = new Date();
        String dateLowerBound = new SimpleDateFormat("yyyy-MM-dd").format(date);
        int nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1 ;
        String dateUpperBound = String.valueOf(nextYear) + "-12-31";
        String orderQuery = "popularity:desc";

        mService.getUpcomingGames(dateLowerBound, dateUpperBound, orderQuery).enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //TODO: check 404, 403 etc
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onGamesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getGame(@NonNull String gameId, @NonNull final GetGameCallback callback) {
        String fields = "name,summary,storyline,cover,first_release_date,total_rating," +
                "aggregated_rating,screenshots,videos,platforms.name" ;
        String expanderString = "platforms";
        mService.getGameByIds(gameId, fields, expanderString).enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //TODO: check 404, 403 etc
                if (!response.isSuccessful() || response.body() == null
                        ||response.body().isEmpty()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onGameLoaded(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void searchForGames(@NonNull String searchKeyword, @NonNull final LoadGamesCallback callback) {
        String searchTerm = searchKeyword;
        mService.searchForGames(searchTerm).enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                //TODO: check 404, 403 etc
                if (!response.isSuccessful() || response.body() == null
                        ||response.body().isEmpty()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onGamesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void refreshSearchedGames() {

    }

    @Override
    public void refreshPopularGames() {

    }

    @Override
    public void refreshMostAnticipatedGames() {

    }

    @Override
    public void refreshUpcomingGames() {

    }
}
