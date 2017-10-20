package com.raju.joel.gamerinside.newsdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.util.ActivityUtils;


public class NewsDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEWS_ID = "NEWS_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        String newsId = getIntent().getStringExtra(EXTRA_NEWS_ID);
        NewsDetailFragment newsDetailFragment = (NewsDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (newsDetailFragment == null) {
            newsDetailFragment = NewsDetailFragment.newInstance(newsId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    newsDetailFragment, R.id.contentFrame);
        }

        new NewsDetailPresenter(newsId,
                DataProvider.provideNewsRepository(getApplicationContext()),
                newsDetailFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
