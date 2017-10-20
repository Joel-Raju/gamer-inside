package com.raju.joel.gamerinside.newsdetail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.raju.joel.gamerinside.data.NewsArticle;
import com.raju.joel.gamerinside.data.source.NewsDataSource;
import com.raju.joel.gamerinside.data.source.NewsRepository;

/**
 * Created by Joel on 14-Sep-17.
 */

public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    @Nullable
    private String mNewsId;

    private final NewsRepository mNewsRepository;

    private final NewsDetailContract.View mNewsDetailView;

    public NewsDetailPresenter(@Nullable String newsId,
                               @NonNull NewsRepository newsRepository,
                               @NonNull NewsDetailContract.View newsDetailView) {
        mNewsId = newsId;
        mNewsRepository = newsRepository;
        mNewsDetailView = newsDetailView;

        mNewsDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        openNewsDetail();
    }

    private void openNewsDetail() {
        if (mNewsId == null || mNewsId.isEmpty()) {
            mNewsDetailView.showMissingNewsArticle();
            return;
        }

        mNewsDetailView.setLoadingIndicator(true);
        mNewsRepository.getNewsArticle(mNewsId, new NewsDataSource.GetNewsArticleCallback() {
            @Override
            public void onNewsArticleLoaded(NewsArticle article) {
                if (!mNewsDetailView.isActive()) {
                    return;
                }

                mNewsDetailView.setLoadingIndicator(false);
                if (null == article) {
                    mNewsDetailView.showMissingNewsArticle();
                } else {
                    showNewsDetail(article);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (!mNewsDetailView.isActive()) {
                    return;
                }
                mNewsDetailView.showMissingNewsArticle();
            }
        });
    }

    private void showNewsDetail(@NonNull NewsArticle article) {
        if (!isNullArticle(article)) {
            mNewsDetailView.showNewsArticle(article);
        }
        // TODO handle null case here
    }

    private boolean isNullArticle(@NonNull NewsArticle article) {
        return (article.getCreatedTimestamp() == null || article.getCreatedTimestamp().isEmpty())||
                (article.getSummary() == null || article.getSummary().isEmpty()) ||
                (article.getPulseImage().getCloudId() == null || article.getPulseImage().getCloudId().isEmpty());
    }

    /**
     * Use this to show webview
     */
    @Override
    public void gotoNewsArticle() {
//        Intent i = new Intent()
    }
}
