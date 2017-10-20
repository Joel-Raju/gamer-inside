package com.raju.joel.gamerinside.gamedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.injection.DataProvider;
import com.raju.joel.gamerinside.util.ActivityUtils;

public class GameDetailActivity extends AppCompatActivity {

    public static final String EXTRA_GAME_ID = "GAME_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        String gameId = getIntent().getStringExtra(EXTRA_GAME_ID);
        GameDetailFragment gameDetailfragment = (GameDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (gameDetailfragment == null) {
            gameDetailfragment = GameDetailFragment.newInstance(gameId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    gameDetailfragment, R.id.contentFrame);
        }

        new GameDetailPresenter(gameId,
                DataProvider.provideGamesRepository(getApplicationContext()),
                gameDetailfragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
