package com.raju.joel.gamerinside.search;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.source.GamesDataSource;
import com.raju.joel.gamerinside.data.source.GamesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 14-Oct-17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final GamesRepository mGamesRepository;

    private final SearchContract.View mView;

    private boolean mFirstLoad = true;

    public SearchPresenter(@NonNull GamesRepository repository,
                           @NonNull SearchContract.View view) {
        mGamesRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadSearchResults(String searchTerm) {
        loadSearchResults(searchTerm, false);
    }

    @Override
    public void clearSearchedResults() {
        mGamesRepository.clearSearchedGames();
        mView.showSearchResults(new ArrayList<Game>(0));
    }

    @Override
    public void openGameDetail(Game game) {
        mView.showGameDetailUi(game.getId());
    }

    private void loadSearchResults(String searchTerm, final boolean showLoadingUi) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            mView.showSearchError();
            return;
        }
        mGamesRepository.searchForGames(searchTerm, new GamesDataSource.LoadGamesCallback() {
            @Override
            public void onGamesLoaded(List<Game> games) {
                if (!mView.isActive()) {
                    return;
                }
                processSearchResults(games);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showSearchError();
            }
        });
    }

    void processSearchResults(List<Game> games) {
        if (games == null || games.isEmpty()) {
            mView.showSearchError();
            return;
        }
        mView.showSearchResults(games);
    }
}
