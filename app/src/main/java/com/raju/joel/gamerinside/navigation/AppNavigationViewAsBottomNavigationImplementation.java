package com.raju.joel.gamerinside.navigation;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;


import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.navigation.NavigationModel.NavigationItemEnum;
/**
 * Created by Joel on 02-Sep-17.
 */

public class AppNavigationViewAsBottomNavigationImplementation
        extends AppNavigationViewAbstractImplementation
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;


    public AppNavigationViewAsBottomNavigationImplementation(BottomNavigationView navigationView) {
        mBottomNavigationView = navigationView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final NavigationItemEnum navItem = NavigationItemEnum.getById(item.getItemId());
        itemSelected(navItem);
        return false;
    }



    @Override
    public void setupView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
}
