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

    void getGames(@NonNull LoadGamesCallback callback);

    void getGame(@NonNull String gameId, @NonNull GetGameCallback callback);


}
