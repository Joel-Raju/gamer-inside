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
        getPopularGamesFromRemoteDataSource(callback);
        //TODO - implement a caching mechanism
//        if (mPopularGamesCache != null && !mPopularGamesCacheIsDirty) {
//            callback.onGamesLoaded(new ArrayList<>(mPopularGamesCache.values()));
//            return;
//        } else {
//            getPopularGamesFromRemoteDataSource(callback);
//        }
    }

    @Override
    public void getMostAnticipatedGames(@NonNull LoadGamesCallback callback) {
        getMostAnticipatedGamesFromRemoteDataSource(callback);
        //TODO - implement a caching mechanism
//        if (mMostAnticipatedGamesCache != null && !mMostAnticipatedGamesCacheIsDirty) {
//            callback.onGamesLoaded(new ArrayList<>(mMostAnticipatedGamesCache.values()));
//            return;
//        } else {
//            getMostAnticipatedGamesFromRemoteDataSource(callback);
//        }
    }

    @Override
    public void getUpcomingGames(@NonNull LoadGamesCallback callback) {
        getUpcomingGamesFromRemoteDataSource(callback);
        //TODO - implement a caching mechanism
//        if (mUpcomingGamesCache != null && !mUpcomingGamesCacheIsDirty) {
//            callback.onGamesLoaded(new ArrayList<>(mUpcomingGamesCache.values()));
//            return;
//        } else {
//            getUpcomingGamesFromRemoteDataSource(callback);
//        }
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
                mPopularGamesCache = refreshPopularGamesCache(games);
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
                mUpcomingGamesCache =  refreshUpcomingGamesCache(games);
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
                mMostAnticipatedGamesCache = refreshMostAnticipatedGamesCache(games);
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
                mSearchedGamesCache = refreshSearchedGamesCache(games);
                callback.onGamesLoaded(new ArrayList<>(mSearchedGamesCache.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    private Map<String, Game> refreshSearchedGamesCache(List<Game> games) {
        if (mSearchedGamesCache == null) {
            return resetCache(mSearchedGamesCache, games, mSearchedGamesCacheIsDirty);
        } else {
            return updateCache(mSearchedGamesCache, games, mSearchedGamesCacheIsDirty);
        }
    }

    private Map<String, Game> refreshPopularGamesCache(List<Game> games) {
        if (mPopularGamesCache == null) {
            return resetCache(mPopularGamesCache, games, mPopularGamesCacheIsDirty);
        } else {
            return updateCache(mPopularGamesCache, games, mPopularGamesCacheIsDirty);
        }
    }

    private Map<String, Game> refreshUpcomingGamesCache(List<Game> games) {
        if(mUpcomingGamesCache == null) {
            return resetCache(mUpcomingGamesCache, games, mUpcomingGamesCacheIsDirty);
        } else {
            return updateCache(mUpcomingGamesCache, games, mUpcomingGamesCacheIsDirty);
        }
    }

    private Map<String, Game> refreshMostAnticipatedGamesCache(List<Game> games) {
        if(mMostAnticipatedGamesCache == null) {
            return resetCache(mMostAnticipatedGamesCache, games, mMostAnticipatedGamesCacheIsDirty);
        } else {
            return updateCache(mMostAnticipatedGamesCache, games, mMostAnticipatedGamesCacheIsDirty);
        }
    }

    @Nullable
    private Game getGameWithId(@NonNull String id) {
        if (mPopularGamesCache == null || mPopularGamesCache.isEmpty()) {
            return null;
        } else {
            return mPopularGamesCache.get(id);
        }
    }

    private Map<String, Game> resetCache(Map<String, Game> cache, List<Game> gamesData, boolean cacheFlag) {
        cache = new LinkedHashMap<>();
        cache.clear();
        for (Game game: gamesData) {
            cache.put(game.getId(), game);
        }
        cacheFlag = false;
        return cache;
    }

    private Map<String, Game> updateCache(Map<String, Game> cache, List<Game> gamesData, boolean cacheFlag) {
        if (cache == null) {
            cache = new LinkedHashMap<>();
            cache.clear();
        }

        for (Game game: gamesData) {
            cache.put(game.getId(), game);
        }
        cacheFlag = false;
        return cache;
    }

    @Override
    public void clearSearchedGames() {
        mSearchedGamesCache = resetCache(mSearchedGamesCache, new ArrayList<Game>(0), mSearchedGamesCacheIsDirty);
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
