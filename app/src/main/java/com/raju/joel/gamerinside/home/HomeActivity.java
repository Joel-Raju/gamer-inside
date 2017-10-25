package com.raju.joel.gamerinside.home;

import android.os.Bundle;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        trySetupNavigationView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
