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
import com.raju.joel.gamerinside.home.HomeActivity;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.navigation.AppNavigationView;
import com.raju.joel.gamerinside.navigation.AppNavigationViewAsBottomNavigationImplementation;
import com.raju.joel.gamerinside.navigation.NavigationModel;
import com.raju.joel.gamerinside.news.NewsFragment;
import com.raju.joel.gamerinside.news.NewsPresenter;
import com.raju.joel.gamerinside.util.ActivityUtils;


public abstract class BaseActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private AppNavigationView mAppNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void trySetupNavigationView(View view) {
        final BottomNavigationView mBottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        if (mBottomNavigationView != null) {
            mAppNavigationView = new AppNavigationViewAsBottomNavigationImplementation(mBottomNavigationView);
            mAppNavigationView.activityReady(this, getSelfNavDrawerItem());
        }
    }

    protected NavigationModel.NavigationItemEnum getSelfNavDrawerItem() {
        return NavigationModel.NavigationItemEnum.NEWS;
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        //View view = getLayoutInflater().inflate(layoutResID, null);
        //trySetupNavigationView(view);
        super.setContentView(layoutResID);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }


    public void showNews() {
        NewsFragment newsFragment =
                (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), newsFragment, R.id.contentFrame);
        }

        NewsPresenter newsPresenter = new NewsPresenter(
                DataProvider.provideNewsRepository(this), newsFragment);
    }


}
