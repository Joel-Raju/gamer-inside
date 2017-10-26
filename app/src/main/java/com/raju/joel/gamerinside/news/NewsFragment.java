package com.raju.joel.gamerinside.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.NewsArticle;
import com.raju.joel.gamerinside.newsdetail.NewsDetailActivity;
import com.raju.joel.gamerinside.newsdetail.NewsListener;
import com.raju.joel.gamerinside.ui.OnBottomReachedListener;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsContract.View, NewsListener,
        OnBottomReachedListener {

    private NewsContract.Presenter mPresenter;

    private NewsAdapter mNewsAdapter;

    private RelativeLayout mContent;

    private RelativeLayout mContentLoading;


    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsAdapter =  new NewsAdapter(getContext(), new ArrayList<NewsArticle>(0), this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.news_recycler_view);
        mContent = (RelativeLayout) root.findViewById(R.id.content);
        mContentLoading = (RelativeLayout) root.findViewById(R.id.content_loading_progress);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mNewsAdapter);
        
        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        toggleContentVisibility(active);
    }

    private void toggleContentVisibility(boolean visible) {
        if (!visible) {
            mContentLoading.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
        } else {
            mContent.setVisibility(View.GONE);
            mContentLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNews(List<NewsArticle> newsArticles) {
        mNewsAdapter.replaceData(newsArticles);
    }

    @Override
    public void showLoadingNewsError() {

    }

    @Override
    public void showNewsArticleDetailUi(String newsArticleId) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, newsArticleId);
        startActivity(intent);
    }

    @Override
    public void onBottomReached() {
        mPresenter.loadNews(false);
    }

    @Override
    public void onNewsArticleClickListener(NewsArticle clickedNewsArticle) {
        mPresenter.openNewsArticleDetails(clickedNewsArticle);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
