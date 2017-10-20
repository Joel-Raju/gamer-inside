package com.raju.joel.gamerinside.news;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.NewsArticle;
import com.raju.joel.gamerinside.data.source.NewsDataSource;
import com.raju.joel.gamerinside.data.source.NewsRepository;

import java.util.List;

/**
 * Created by Joel on 04-Sep-17.
 */

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsRepository mNewsRepository;

    private final NewsContract.View mNewsView;

    private boolean mFirstLoad = true;

    public NewsPresenter(@NonNull NewsRepository newsRepository,
                         @NonNull NewsContract.View newsView) {
        mNewsRepository = newsRepository;
        mNewsView = newsView;

        mNewsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadNews(false);
    }

    @Override
    public void loadNews(boolean forceUpdate) {
        loadNews(forceUpdate || mFirstLoad, false);
        mFirstLoad = false;
    }

    private void loadNews(boolean forceUpdate, final boolean showLoadingUi) {
        if (showLoadingUi) {
            mNewsView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            mNewsRepository.refreshNews();
        }

        mNewsRepository.getNews(new NewsDataSource.LoadedNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsArticle> newsArticles) {
                List<NewsArticle> articles = newsArticles;

                //TODO: fix the below commented lines
//                if (!mNewsView.isActive()) {
//                    return;
//                }

                if (showLoadingUi) {
                    mNewsView.setLoadingIndicator(false);
                }

                processNews(articles);
            }

            @Override
            public void onDataNotAvailable() {
                if (!mNewsView.isActive()) {
                    return;
                }
                mNewsView.showLoadingNewsError();
            }
        });
    }

    private void processNews(List<NewsArticle> newsArticles) {
        if (newsArticles.isEmpty()) {

        } else {
            mNewsView.showNews(newsArticles);
        }
    }

    @Override
    public void openNewsArticleDetails(@NonNull NewsArticle requestedArticle) {
        mNewsView.showNewsArticleDetailUi(requestedArticle.getId());
    }
}
