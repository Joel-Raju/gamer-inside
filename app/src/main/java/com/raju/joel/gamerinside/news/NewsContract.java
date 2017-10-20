package com.raju.joel.gamerinside.news;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.BasePresenter;
import com.raju.joel.gamerinside.BaseView;
import com.raju.joel.gamerinside.data.NewsArticle;

import java.util.List;

/**
 * Created by Joel on 04-Sep-17.
 */

public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showNews(List<NewsArticle> newsArticles);

        void showLoadingNewsError();

        void showNewsArticleDetailUi(String newsArticleId);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadNews(boolean forceUpdate);

        void openNewsArticleDetails(@NonNull NewsArticle newsArticle);
    }
}
