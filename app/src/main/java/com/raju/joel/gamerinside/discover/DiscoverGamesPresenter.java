package com.raju.joel.gamerinside.discover;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.source.GamesDataSource;
import com.raju.joel.gamerinside.data.source.GamesRepository;

import java.util.List;

/**
 * Created by Joel on 19-Sep-17.
 */

public class DiscoverGamesPresenter implements DiscoverGamesContract.Presenter {

    private final GamesRepository mRepository;

    private final DiscoverGamesContract.View mView;

    private boolean mFirstLoad = true;

    public DiscoverGamesPresenter(@NonNull GamesRepository repository,
                                  @NonNull DiscoverGamesContract.View view) {
        mRepository = repository;
        mView = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setLoadingIndicator(true);
        loadPopularGames(false);
        loadMostAnticipatedGames(false);
        loadUpcomingGames(false);
    }

    @Override
    public void loadPopularGames(boolean forceUpdate) {
        loadPopularGames(forceUpdate || mFirstLoad, false);
        mFirstLoad = false;
    }

    private void loadPopularGames(boolean forceUpdate, final boolean showLoadingUi) {
        if (showLoadingUi) {
            mView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            mRepository.refreshPopularGames();
        }

        mRepository.getPopularGames(new GamesDataSource.LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                if (!mView.isActive()) {
                    return;
                }
                if (showLoadingUi) {
                    mView.setLoadingIndicator(false);
                }

                processPopularGames(games);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showLoadingPopularGamesError();
            }
        });
    }

    @Override
    public void loadMostAnticipatedGames(boolean forceUpdate) {
        loadMostAnticipatedGames(mFirstLoad || forceUpdate, false);
        mFirstLoad = false;
    }

    private void loadMostAnticipatedGames(boolean forceUpdate, final boolean showLoadingUi) {
        if (showLoadingUi) {
            mView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mRepository.refreshMostAnticipatedGames();
        }

        mRepository.getMostAnticipatedGames(new GamesDataSource.LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                if (!mView.isActive()) {
                    return;
                }
                if (showLoadingUi) {
                    mView.setLoadingIndicator(false);
                }
                processMostAnticipatedGames(games);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showLoadingMostlyAnticipatedGamesError();
            }
        });
    }

    @Override
    public void loadUpcomingGames(boolean forceUpdate) {
        loadUpcomingGames(mFirstLoad || forceUpdate, false);
        mFirstLoad = false;
    }

    public void loadUpcomingGames(boolean forceUpdate, final boolean showLoadingUi) {
        if (showLoadingUi) {
            mView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mRepository.refreshUpcomingGames();
        }

        mRepository.getUpcomingGames(new GamesDataSource.LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                if (!mView.isActive()) {
                    return;
                }
                if (showLoadingUi) {
                    mView.setLoadingIndicator(false);
                }
                processUpcomingGames(games);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mView.isActive()) {
                    return;
                }
                mView.setLoadingIndicator(false);
                mView.showLoadingUpcomingGamesError();
            }
        });
    }

    @Override
    public void openGameDetail(@NonNull Game game) {
        mView.showGameDetailUi(game.getId());
    }


    private void processPopularGames(List<Game> games) {
        if (games.isEmpty()) {
            mView.showLoadingPopularGamesError();
        } else {
            mView.showPopularGames(games);
        }
    }

    private void processMostAnticipatedGames(List<Game> games) {
        if (games.isEmpty()) {
            mView.showLoadingMostlyAnticipatedGamesError();
        } else {
            mView.showMostAnticipatedGames(games);
        }
    }

    private void processUpcomingGames(List<Game> games) {
        if (games.isEmpty()) {
            mView.showLoadingUpcomingGamesError();
        } else {
            mView.showUpcomingGames(games);
        }
    }

}
