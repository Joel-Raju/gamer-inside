package com.raju.joel.gamerinside.data.source;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raju.joel.gamerinside.data.Game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation to load games from the data source
 */
public class GamesRepository implements GamesDataSource {

    private static GamesRepository INSTANCE = null;

    private final GamesDataSource mGamesRemoteDataSource;

    Map<String, Game> mCachedGames;

    boolean mCacheIsDirty = false;


    private GamesRepository(@NonNull GamesDataSource gamesRemoteDataSource) {
        mGamesRemoteDataSource = gamesRemoteDataSource;
    }

    private static GamesRepository getInstance(GamesDataSource gamesRemoteDataSource) {
        if(INSTANCE == null) {
            INSTANCE = new GamesRepository(gamesRemoteDataSource);
        }
        return INSTANCE;
    }

    private static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getGames(@NonNull LoadGamesCallback callback) {

        if (mCachedGames != null && !mCacheIsDirty) {
            callback.onGamesLoaded(new ArrayList<>(mCachedGames.values()));
            return;
        } else {
            getGamesFromRemoteDataSource(callback);
        }

    }

    @Override
    public void getGame(@NonNull String gameId, @NonNull final GetGameCallback callback) {
        Game cachedGame = getGameWithId(gameId);

        if (cachedGame != null) {
            callback.onGameLoaded(cachedGame);
            return;
        }

        mGamesRemoteDataSource.getGame(gameId, new GetGameCallback() {
            @Override
            public void onGameLoaded(Game game) {
                if (mCachedGames == null) {
                    mCachedGames = new LinkedHashMap<>();
                }
                mCachedGames.put(game.getId(), game);
                callback.onGameLoaded(game);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getGamesFromRemoteDataSource(@NonNull final LoadGamesCallback callback) {
        mGamesRemoteDataSource.getGames(new LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                refreshCache(games);
                callback.onGamesLoaded(new ArrayList<>(mCachedGames.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Game> games) {
        if(mCachedGames == null) {
            mCachedGames = new LinkedHashMap<>();
        }
        mCachedGames.clear();
        for (Game game: games) {
            mCachedGames.put(game.getId(), game);
        }
        mCacheIsDirty = false;
    }

    @Nullable
    private Game getGameWithId(@NonNull String id) {
        if (mCachedGames == null || mCachedGames.isEmpty()) {
            return null;
        } else {
            return mCachedGames.get(id);
        }
    }
}
