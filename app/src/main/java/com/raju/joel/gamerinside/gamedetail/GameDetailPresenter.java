package com.raju.joel.gamerinside.gamedetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raju.joel.gamerinside.BuildConfig;
import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.source.GamesDataSource;
import com.raju.joel.gamerinside.data.source.GamesRepository;

/**
 * Created by Joel on 02-Oct-17.
 */

public class GameDetailPresenter implements GameDetailContract.Presenter {

    private String mGameId;

    private GamesRepository mGamesRepository;

    private GameDetailContract.View mGameDetailView;


    public GameDetailPresenter(@Nullable String gameId,
                               @NonNull GamesRepository gamesRepository,
                               @NonNull GameDetailContract.View gameDetailView) {
        mGameId = gameId;
        mGamesRepository = gamesRepository;
        mGameDetailView = gameDetailView;

        mGameDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mGameId == null || mGameId.isEmpty()) {
            mGameDetailView.showLoadingGameDetailError();
            return;
        }
        mGameDetailView.setLoadingIndicator(true);
        loadGameDetail(mGameId);
    }

    private void loadGameDetail(String gameId) {
        mGamesRepository.getGame(gameId, new GamesDataSource.GetGameCallback() {
            @Override
            public void onGameLoaded(Game game) {
                if (!mGameDetailView.isActive()) {
                    return;
                }

                mGameDetailView.setLoadingIndicator(false);

                if (game == null) {
                    mGameDetailView.showLoadingGameDetailError();
                } else  {
                    processGameDetail(game);
                }

            }

            @Override
            public void onDataNotAvailable() {
                if (!mGameDetailView.isActive()) {
                    return;
                }
                mGameDetailView.showLoadingGameDetailError();
            }
        });
    }

    private void processGameDetail(@NonNull Game game) {
        mGameDetailView.showGameDetail(game);
        if (game.getScreenshots() != null && !game.getScreenshots().isEmpty()) {
            mGameDetailView.showGameImageGallery(game.getScreenshots());
        }
        if (game.getVideos() != null && !game.getVideos().isEmpty()) {
            mGameDetailView.showGameVideoGallery(game.getVideos());
        }
    }
}
