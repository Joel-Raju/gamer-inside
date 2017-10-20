package com.raju.joel.gamerinside.navigation;


import android.app.Activity;
import android.content.Context;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.home.HomeActivity;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.navigation.NavigationModel.NavigationItemEnum;
import com.raju.joel.gamerinside.news.NewsFragment;
import com.raju.joel.gamerinside.news.NewsPresenter;
import com.raju.joel.gamerinside.ui.BaseActivity;
import com.raju.joel.gamerinside.util.ActivityUtils;

/**
 * Created by Joel on 02-Sep-17.
 */

public abstract class AppNavigationViewAbstractImplementation implements AppNavigationView {

    protected Activity mActivity;

    protected NavigationItemEnum mSelfItem;


    @Override
    public void activityReady(Activity activity, NavigationModel.NavigationItemEnum item) {
        mActivity = activity;
        mSelfItem = item;
        setupView();
    }

    @Override
    public void setupView() {

    }

    @Override
    public void updateNavigationItems() {

    }

    @Override
    public void displayNavigationItems() {

    }

    @Override
    public void itemSelected(NavigationModel.NavigationItemEnum item) {
//        if (item == NavigationItemEnum.NEWS) {
//            NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//            if (newsFragment == null) {
//                newsFragment = NewsFragment.newInstance();
//
//                ActivityUtils.addFragmentToActivity(
//                        getContext().  getF gets getSupportFragmentManager(), newsFragment, R.id.contentFrame);
//            }
//
//        NewsPresenter newsPresenter = new NewsPresenter(
//                DataProvider.provideNewsRepository(HomeActivity.this), newsFragment);
//
//        } else if (item == NavigationItemEnum.DISCOVER) {
//
//        } else if (item == NavigationItemEnum.SEARCH) {
//
//        }
    }

    @Override
    public void showNavigation() {

    }

    public Context getContext() {
//        (BaseActivity) mActivity.shownew

        return mActivity;

    }
}
