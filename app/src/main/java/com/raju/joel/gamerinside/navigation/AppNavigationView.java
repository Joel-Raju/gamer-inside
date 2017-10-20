package com.raju.joel.gamerinside.navigation;

import android.app.Activity;

/**
 * Created by Joel on 02-Sep-17.
 */

public interface AppNavigationView {

    void activityReady(Activity activity, NavigationModel.NavigationItemEnum item);

    void setupView();

    void updateNavigationItems();

    void displayNavigationItems();

    void itemSelected(NavigationModel.NavigationItemEnum item);

    void showNavigation();


}
