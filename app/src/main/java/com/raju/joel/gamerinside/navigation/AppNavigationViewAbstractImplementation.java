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

    private NavigationListener mNavigationListener;


    @Override
    public void activityReady(Activity activity, NavigationModel.NavigationItemEnum item, NavigationListener navigationListener) {
        mActivity = activity;
        mSelfItem = item;
        mNavigationListener = navigationListener;
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
        if (item == NavigationItemEnum.NEWS) {
            mNavigationListener.showNewFragment();
        } else if (item == NavigationItemEnum.DISCOVER) {
            mNavigationListener.showDiscoverFragment();
        } else if (item == NavigationItemEnum.SEARCH) {
            mNavigationListener.showSearchFragment();
        }
    }

    @Override
    public void showNavigation() {

    }

    public Context getContext() {
//        (BaseActivity) mActivity.shownew

        return mActivity;

    }
}
