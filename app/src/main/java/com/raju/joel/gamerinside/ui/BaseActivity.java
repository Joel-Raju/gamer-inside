package com.raju.joel.gamerinside.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.discover.DiscoverGamesFragment;
import com.raju.joel.gamerinside.discover.DiscoverGamesPresenter;
import com.raju.joel.gamerinside.home.HomeActivity;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.navigation.AppNavigationView;
import com.raju.joel.gamerinside.navigation.AppNavigationViewAsBottomNavigationImplementation;
import com.raju.joel.gamerinside.navigation.NavigationListener;
import com.raju.joel.gamerinside.navigation.NavigationModel;
import com.raju.joel.gamerinside.news.NewsFragment;
import com.raju.joel.gamerinside.news.NewsPresenter;
import com.raju.joel.gamerinside.search.SearchFragment;
import com.raju.joel.gamerinside.search.SearchPresenter;
import com.raju.joel.gamerinside.util.ActivityUtils;


public abstract class BaseActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener, NavigationListener {

    private AppNavigationView mAppNavigationView;

    NewsFragment newsFragment;

    DiscoverGamesFragment discoverGamesFragment;

    SearchFragment searchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_view);

        newsFragment =
                (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        discoverGamesFragment =
                (DiscoverGamesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);


    }

    protected void trySetupNavigationView() {
        final BottomNavigationView mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        if (mBottomNavigationView != null) {
            mAppNavigationView = new AppNavigationViewAsBottomNavigationImplementation(mBottomNavigationView);
            mAppNavigationView.activityReady(this, getSelfNavDrawerItem(), this);
        }
    }

    protected NavigationModel.NavigationItemEnum getSelfNavDrawerItem() {
        return NavigationModel.NavigationItemEnum.NEWS;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }


    @Override
    public void showSearchFragment() {
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            if (ActivityUtils.isFragmentPreviouslyAddedToActivity(getSupportFragmentManager(), searchFragment)) {
                ActivityUtils.attachFragmentToActivity(getSupportFragmentManager(), searchFragment);
            } else {
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        searchFragment, R.id.contentFrame);
            }
            SearchPresenter searchPresenter =
                    new SearchPresenter(DataProvider.provideGamesRepository(this), searchFragment);
        }
    }

    @Override
    public void showNewFragment() {
        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance();
//            if (ActivityUtils.isFragmentPreviouslyAddedToActivity(getSupportFragmentManager(), searchFragment)) {
//                ActivityUtils.detachFragmentFromActivity(getSupportFragmentManager(), searchFragment);
//            }
//
//            if (ActivityUtils.isFragmentPreviouslyAddedToActivity(getSupportFragmentManager(), discoverGamesFragment)) {
//                ActivityUtils.detachFragmentFromActivity(getSupportFragmentManager(), discoverGamesFragment);
//            }

            if (ActivityUtils.isFragmentPreviouslyAddedToActivity(getSupportFragmentManager(), newsFragment)) {
                ActivityUtils.attachFragmentToActivity(getSupportFragmentManager(), newsFragment);
            } else {
                ActivityUtils.addFragmentToActivity(
                        getSupportFragmentManager(), newsFragment, R.id.contentFrame);
            }
        }

        NewsPresenter newsPresenter = new NewsPresenter(
                DataProvider.provideNewsRepository(this), newsFragment);
    }

    @Override
    public void showDiscoverFragment() {
        if (discoverGamesFragment == null) {
            discoverGamesFragment = DiscoverGamesFragment.newInstance();
            if (ActivityUtils.isFragmentPreviouslyAddedToActivity(getSupportFragmentManager(), discoverGamesFragment)) {
                ActivityUtils.attachFragmentToActivity(getSupportFragmentManager(), discoverGamesFragment);
            } else {
                ActivityUtils.addFragmentToActivity(
                        getSupportFragmentManager(), discoverGamesFragment, R.id.contentFrame);
            }
        }

        DiscoverGamesPresenter gamesPresenter = new DiscoverGamesPresenter(
                DataProvider.provideGamesRepository(this), discoverGamesFragment);
    }


}
