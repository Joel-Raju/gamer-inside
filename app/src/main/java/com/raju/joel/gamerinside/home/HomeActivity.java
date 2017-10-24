package com.raju.joel.gamerinside.home;

import android.os.Bundle;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.discover.DiscoverGamesFragment;
import com.raju.joel.gamerinside.discover.DiscoverGamesPresenter;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.news.NewsFragment;
import com.raju.joel.gamerinside.news.NewsPresenter;
import com.raju.joel.gamerinside.search.SearchFragment;
import com.raju.joel.gamerinside.search.SearchPresenter;
import com.raju.joel.gamerinside.ui.BaseActivity;
import com.raju.joel.gamerinside.util.ActivityUtils;

public class HomeActivity extends BaseActivity {

    private static final String ACTIVE_TAB = "ActiveTab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        trySetupNavigationView();

        //News
//        NewsFragment newsFragment =
//                (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (newsFragment == null) {
//            newsFragment = NewsFragment.newInstance();
//
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), newsFragment, R.id.contentFrame);
//        }
//
//        NewsPresenter newsPresenter = new NewsPresenter(
//                DataProvider.provideNewsRepository(HomeActivity.this), newsFragment);


//        DiscoverGamesFragment discoverGamesFragment =
//                (DiscoverGamesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (discoverGamesFragment == null) {
//            discoverGamesFragment = DiscoverGamesFragment.newInstance();
//
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), discoverGamesFragment, R.id.contentFrame);
//        }
//
//        DiscoverGamesPresenter gamesPresenter = new DiscoverGamesPresenter(
//                DataProvider.provideGamesRepository(HomeActivity.this), discoverGamesFragment);


//        SearchFragment searchFragment =
//                (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (searchFragment == null) {
//            searchFragment = SearchFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(
//                    getSupportFragmentManager(), searchFragment, R.id.contentFrame);
//        }
//
//        SearchPresenter searchPresenter =
//                new SearchPresenter(DataProvider.provideGamesRepository(HomeActivity.this), searchFragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
