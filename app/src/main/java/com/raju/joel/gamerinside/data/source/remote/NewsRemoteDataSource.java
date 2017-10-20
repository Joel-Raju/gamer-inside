package com.raju.joel.gamerinside.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raju.joel.gamerinside.data.NewsArticle;
import com.raju.joel.gamerinside.data.source.NewsDataSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Joel on 06-Sep-17.
 */

public class NewsRemoteDataSource implements NewsDataSource {

    private static NewsRemoteDataSource INSTANCE = null;

    private static RemoteService mService;

    private static int NEWS_PAGINATION_OFFSET = 0;

    private static final int NEWS_PAGINATION_LIMIT = 5;


    private NewsRemoteDataSource(@NonNull Context context) {

    }

    public static NewsDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NewsRemoteDataSource(context);
            mService = RemoteServiceUtils.getRemoteService();
        }
        return INSTANCE;
    }

    @Override
    public void getNews(@NonNull final LoadedNewsCallback callback) {

        mService.getLatestNews(NEWS_PAGINATION_LIMIT, NEWS_PAGINATION_OFFSET).enqueue(new Callback<List<NewsArticle>>() {
            @Override
            public void onResponse(Call<List<NewsArticle>> call, Response<List<NewsArticle>> response) {
                if (!response.isSuccessful() || response.code() != RemoteServiceUtils.STATUS_CODE_OK) {
                    callback.onDataNotAvailable();
                    return;
                }
                NEWS_PAGINATION_OFFSET += NEWS_PAGINATION_LIMIT;
                callback.onNewsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<NewsArticle>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getNewsArticle(String newsId, @NonNull final GetNewsArticleCallback callback) {
        mService.getNewsByIds(newsId).enqueue(new Callback<List<NewsArticle>>() {
            @Override
            public void onResponse(Call<List<NewsArticle>> call, Response<List<NewsArticle>> response) {

                // TODO: check for 401 , 402 etc
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable();
                    return;
                }

                //TODO: check and modify this
                List<NewsArticle> newsArticles = response.body();
                callback.onNewsArticleLoaded(newsArticles.get(0));
            }

            @Override
            public void onFailure(Call<List<NewsArticle>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void refreshNews() {

    }
}
