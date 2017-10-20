package com.raju.joel.gamerinside.search;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;
import com.raju.joel.gamerinside.data.Game;

import java.util.List;

/**
 * Created by Joel on 14-Oct-17.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showSearchResults(List<Game> games);

        void showSearchError();

        void showGameDetailUi(String gameId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadSearchResults(String searchTerm);

        void openGameDetail(Game game);
    }
}
