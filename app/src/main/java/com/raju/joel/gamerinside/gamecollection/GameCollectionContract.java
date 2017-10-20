package com.raju.joel.gamerinside.gamecollection;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;

/**
 * Created by Joel on 20-Sep-17.
 */

public class GameCollectionContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isActive);

        void showGameCollection();
    }

    interface Presenter extends BasePresenter {

    }
}
