package com.raju.joel.gamerinside.navigation;

/**
 * Created by Joel on 24-Oct-17.
 */

public interface NavigationListener {

    void showNewFragment();

    void showDiscoverFragment();

    void showSearchFragment();

    void detachFragmentByNavigationItem(NavigationModel.NavigationItemEnum navigation);
}
