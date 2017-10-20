package com.raju.joel.gamerinside.data.source;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.NewsArticle;

import java.util.List;

/**
 * Created by Joel on 04-Sep-17.
 */

public interface NewsDataSource {

    /**
     * This is for the news article list
     */
    interface LoadedNewsCallback {

        void onNewsLoaded(List<NewsArticle> newsArticles);

        void onDataNotAvailable();
    }

    /**
     * This is for a single news article
     */
    interface GetNewsArticleCallback {

        void onNewsArticleLoaded(NewsArticle article);

        void onDataNotAvailable();
    }

    void getNews(@NonNull LoadedNewsCallback callback);

    void getNewsArticle(@NonNull String newsId, @NonNull GetNewsArticleCallback callback);

    void refreshNews();
}
