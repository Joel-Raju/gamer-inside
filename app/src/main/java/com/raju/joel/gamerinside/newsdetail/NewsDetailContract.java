package com.raju.joel.gamerinside.newsdetail;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;
import com.raju.joel.gamerinside.data.NewsArticle;

/**
 * Created by Joel on 14-Sep-17.
 */

public class NewsDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMissingNewsArticle();

        void showNewsArticle(NewsArticle article);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void gotoNewsArticle();
    }
}
