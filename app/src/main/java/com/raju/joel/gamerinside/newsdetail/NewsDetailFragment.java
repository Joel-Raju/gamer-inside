package com.raju.joel.gamerinside.newsdetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.NewsArticle;
import com.squareup.picasso.Picasso;


public class NewsDetailFragment extends Fragment implements NewsDetailContract.View {

    private static final String ARGUMENT_NEWS_ID = "NEWS_ID";

    private static final String IMAGE_BASE_URL = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/";

    private static final String IMAGE_FILE_EXTENSION = ".png";

    private NewsDetailContract.Presenter mPresenter;

    private TextView mNewsSummary;

    private TextView mNewsTitle;

    private TextView mNewsReadMore;

    private ImageView mNewsImage;

    private RelativeLayout mContent;

    private RelativeLayout mContentLoading;


    public NewsDetailFragment() {
        // Required empty public constructor
    }


    public static NewsDetailFragment newInstance(@NonNull String newsId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NEWS_ID, newsId);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(arguments);
        return new NewsDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        mContent = (RelativeLayout) view.findViewById(R.id.content);
        mContentLoading = (RelativeLayout) view.findViewById(R.id.content_loading_progress);
        mNewsTitle = (TextView) view.findViewById(R.id.news_article_title);
        mNewsSummary = (TextView) view.findViewById(R.id.news_article_description);
        mNewsImage = (ImageView) view.findViewById(R.id.news_article_image);
        mNewsReadMore = (TextView) view.findViewById(R.id.go_to_article);


        mNewsReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.gotoNewsArticle();
            }
        });

        return view;
    }


    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
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
    public void showMissingNewsArticle() {
    }

    @Override
    public void showNewsArticle(NewsArticle article) {

        mNewsTitle.setText(article.getTitle());
        mNewsSummary.setText(article.getSummary());
        Picasso.with(getContext())
                .load(getNewsArticleImage(article.getPulseImage().getCloudId()))
                .into(mNewsImage);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    private String getNewsArticleImage(String cloudId) {
        return IMAGE_BASE_URL + cloudId +IMAGE_FILE_EXTENSION;
    }
}
