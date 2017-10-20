package com.raju.joel.gamerinside.data.source;

import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.NewsArticle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joel on 04-Sep-17.
 */

/**
 * Concrete Implementation of NewsDataSource
 */
public class NewsRepository implements NewsDataSource {

    boolean mCacheIsDirty = false;

    private static NewsRepository INSTANCE = null;

    private final NewsDataSource mNewsRemoteDataSource;

    Map<String, NewsArticle> mCachedNewsArticles;

    private NewsRepository(@NonNull NewsDataSource newsRemoteDataSource) {
        mNewsRemoteDataSource = newsRemoteDataSource;
    }

    public static NewsRepository getInstance(@NonNull NewsDataSource newsRemoteDataSource){
        if (INSTANCE == null) {
            INSTANCE = new NewsRepository(newsRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



    @Override
    public void getNews(@NonNull LoadedNewsCallback callback) {

        getNewsFromRemoteDataSource(callback);

        //TODO implement a caching mechanism
//        if (mCachedNewsArticles != null && !mCacheIsDirty) {
//            callback.onNewsLoaded(new ArrayList<>(mCachedNewsArticles.values()));
//            return;
//        }
//
//        if (mCacheIsDirty) {
//            getNewsFromRemoteDataSource(callback);
//        }
    }

    @Override
    public void getNewsArticle(String newsId, @NonNull GetNewsArticleCallback callback) {
        getNewsArticleFromRemoteDataSource(newsId, callback);
    }

    @Override
    public void refreshNews() {
        mCacheIsDirty = true;
    }

    private void getNewsFromRemoteDataSource(@NonNull final LoadedNewsCallback callback) {
        mNewsRemoteDataSource.getNews(new LoadedNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsArticle> newsArticles) {
                if (mCachedNewsArticles == null) {
                    refreshCache(newsArticles);
                } else {
                    updateCache(newsArticles);
                }
                callback.onNewsLoaded(new ArrayList<>(mCachedNewsArticles.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getNewsArticleFromRemoteDataSource(String newsId, @NonNull final GetNewsArticleCallback callback) {
        mNewsRemoteDataSource.getNewsArticle(newsId, new GetNewsArticleCallback() {
            @Override
            public void onNewsArticleLoaded(NewsArticle article) {
                callback.onNewsArticleLoaded(article);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<NewsArticle> newsArticles) {
        mCachedNewsArticles = new LinkedHashMap<>();
        mCachedNewsArticles.clear();
        for (NewsArticle article: newsArticles) {
            mCachedNewsArticles.put(article.getId(), article);
        }
        mCacheIsDirty = false;
    }

    private void updateCache(@NonNull List<NewsArticle> newsArticles) {
        if (mCachedNewsArticles == null) {
            mCachedNewsArticles = new LinkedHashMap<>();
            mCachedNewsArticles.clear();
        }
        for (NewsArticle article: newsArticles) {
            mCachedNewsArticles.put(article.getId(), article);
        }
        mCacheIsDirty = false;
    }
}
