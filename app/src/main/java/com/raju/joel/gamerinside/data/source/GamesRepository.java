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

    Map<String, Game> mPopularGamesCache;

    Map<String, Game> mUpcomingGamesCache;

    Map<String, Game> mMostAnticipatedGamesCache;

    Map<String, Game> mSearchedGamesCache;

    boolean mPopularGamesCacheIsDirty = false;

    boolean mUpcomingGamesCacheIsDirty = false;

    boolean mMostAnticipatedGamesCacheIsDirty = false;

    boolean mSearchedGamesCacheIsDirty = false;


    private GamesRepository(@NonNull GamesDataSource gamesRemoteDataSource) {
        mGamesRemoteDataSource = gamesRemoteDataSource;
    }

    public static GamesRepository getInstance(GamesDataSource gamesRemoteDataSource) {
        if(INSTANCE == null) {
            INSTANCE = new GamesRepository(gamesRemoteDataSource);
        }
        return INSTANCE;
    }

    private static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getPopularGames(@NonNull LoadGamesCallback callback) {
        if (mPopularGamesCache != null && !mPopularGamesCacheIsDirty) {
            callback.onGamesLoaded(new ArrayList<>(mPopularGamesCache.values()));
            return;
        } else {
            getPopularGamesFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getMostAnticipatedGames(@NonNull LoadGamesCallback callback) {
        if (mMostAnticipatedGamesCache != null && !mMostAnticipatedGamesCacheIsDirty) {
            callback.onGamesLoaded(new ArrayList<>(mMostAnticipatedGamesCache.values()));
            return;
        } else {
            getMostAnticipatedGamesFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getUpcomingGames(@NonNull LoadGamesCallback callback) {
        if (mUpcomingGamesCache != null && !mUpcomingGamesCacheIsDirty) {
            callback.onGamesLoaded(new ArrayList<>(mUpcomingGamesCache.values()));
            return;
        } else {
            getUpcomingGamesFromRemoteDataSource(callback);
        }
    }

    @Override
    public void getGame(@NonNull String gameId, @NonNull final GetGameCallback callback) {

        //TODO: implement a caching mechanism
//        Game cachedGame = getGameWithId(gameId);
//
//        if (cachedGame != null) {
//            callback.onGameLoaded(cachedGame);
//            return;
//        }

        mGamesRemoteDataSource.getGame(gameId, new GetGameCallback() {
            @Override
            public void onGameLoaded(Game game) {
                if (mPopularGamesCache == null) {
                    mPopularGamesCache = new LinkedHashMap<>();
                }
                mPopularGamesCache.put(game.getId(), game);
                callback.onGameLoaded(game);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getPopularGamesFromRemoteDataSource(@NonNull final LoadGamesCallback callback) {
        mGamesRemoteDataSource.getPopularGames(new LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                refreshPopularGamesCache(games);
                callback.onGamesLoaded(new ArrayList<>(mPopularGamesCache.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getUpcomingGamesFromRemoteDataSource(@NonNull final LoadGamesCallback callback) {
        mGamesRemoteDataSource.getUpcomingGames(new LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                refreshUpcomingGamesCache(games);
                callback.onGamesLoaded(new ArrayList<>(mUpcomingGamesCache.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getMostAnticipatedGamesFromRemoteDataSource(@NonNull final LoadGamesCallback callback) {
        mGamesRemoteDataSource.getMostAnticipatedGames(new LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                refreshMostAnticipatedGamesCache(games);
                callback.onGamesLoaded(new ArrayList<>(mMostAnticipatedGamesCache.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void searchForGames(@NonNull String searchKeyword, @NonNull final LoadGamesCallback callback) {
        mGamesRemoteDataSource.searchForGames(searchKeyword, new LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                refreshSearchedGamesCache(games);
                callback.onGamesLoaded(new ArrayList<Game>(mSearchedGamesCache.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    private void refreshSearchedGamesCache(List<Game> games) {
        if (mSearchedGamesCache == null) {
            mSearchedGamesCache = new LinkedHashMap<>();
        }
        mSearchedGamesCache.clear();
        for (Game game: games) {
            mSearchedGamesCache.put(game.getId(), game);
        }
        mSearchedGamesCacheIsDirty = false;
    }

    private void refreshPopularGamesCache(List<Game> games) {
        if(mPopularGamesCache == null) {
            mPopularGamesCache = new LinkedHashMap<>();
        }
        mPopularGamesCache.clear();
        for (Game game: games) {
            mPopularGamesCache.put(game.getId(), game);
        }
        mPopularGamesCacheIsDirty = false;
    }

    private void refreshUpcomingGamesCache(List<Game> games) {
        if(mUpcomingGamesCache == null) {
            mUpcomingGamesCache = new LinkedHashMap<>();
        }
        mUpcomingGamesCache.clear();
        for (Game game: games) {
            mUpcomingGamesCache.put(game.getId(), game);
        }
        mUpcomingGamesCacheIsDirty = false;
    }

    private void refreshMostAnticipatedGamesCache(List<Game> games) {
        if(mMostAnticipatedGamesCache == null) {
            mMostAnticipatedGamesCache = new LinkedHashMap<>();
        }
        mMostAnticipatedGamesCache.clear();
        for (Game game: games) {
            mMostAnticipatedGamesCache.put(game.getId(), game);
        }
        mMostAnticipatedGamesCacheIsDirty = false;
    }

    @Nullable
    private Game getGameWithId(@NonNull String id) {
        if (mPopularGamesCache == null || mPopularGamesCache.isEmpty()) {
            return null;
        } else {
            return mPopularGamesCache.get(id);
        }
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

    @Override
    public void refreshSearchedGames() {

    }
}
