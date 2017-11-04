package com.raju.joel.gamerinside.data.source;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.Game;

import java.util.List;


public interface GamesDataSource {

    interface LoadGamesCallback {

        void onGamesLoaded(List<Game> games);

        void onDataNotAvailable();
    }

    interface GetGameCallback {

        void onGameLoaded(Game game);

        void onDataNotAvailable();
    }

    void getPopularGames(@NonNull LoadGamesCallback callback);

    void getMostAnticipatedGames(@NonNull LoadGamesCallback callback);

    void getUpcomingGames(@NonNull LoadGamesCallback callback);

    void getGame(@NonNull String gameId, @NonNull GetGameCallback callback);

    void searchForGames(@NonNull String searchKeyword, boolean resetSearch, @NonNull LoadGamesCallback callback);

    void refreshPopularGames();

    void refreshMostAnticipatedGames();

    void refreshUpcomingGames();

    void refreshSearchedGames();

    void clearSearchedGames();

}
