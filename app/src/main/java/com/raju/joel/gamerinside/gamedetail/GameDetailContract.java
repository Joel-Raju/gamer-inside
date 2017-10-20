package com.raju.joel.gamerinside.gamedetail;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;
import com.raju.joel.gamerinside.data.Game;

import java.util.List;


public interface GameDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showGameDetail(Game game);

        void showGameReviewCollection();

        void showGameImageGallery(List<Game.ScreenShot> screenShots);

        void showGameVideoGallery(List<Game.Video> videos);

//        void showGameStoryLine(); // can be null

        void showLoadingGameDetailError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}
