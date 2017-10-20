package com.raju.joel.gamerinside.discover;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;
import com.raju.joel.gamerinside.data.Game;

import java.util.List;

/**
 * Created by Joel on 19-Sep-17.
 */

public class DiscoverGamesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showPopularGames(List<Game> games);

        void showMostAnticipatedGames(List<Game> games);

        void showUpcomingGames(List<Game> games);

        void showLoadingPopularGamesError();

        void showLoadingMostlyAnticipatedGamesError();

        void showLoadingUpcomingGamesError();

        void showGameDetailUi(String newsArticleId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadPopularGames(boolean forceUpdate);

        void loadMostAnticipatedGames(boolean forceUpdate);

        void loadUpcomingGames(boolean forceUpdate);

        void openGameDetail(@NonNull Game game);
    }
}
