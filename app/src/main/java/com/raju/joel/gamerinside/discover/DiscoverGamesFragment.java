package com.raju.joel.gamerinside.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.gamecollection.GameCollectionAdapter;
import com.raju.joel.gamerinside.gamedetail.*;

import com.raju.joel.gamerinside.gamedetail.GameListener;
import com.raju.joel.gamerinside.ui.OnBottomReachedListener;

import java.util.ArrayList;
import java.util.List;


public class DiscoverGamesFragment extends Fragment implements DiscoverGamesContract.View,
        GameListener {

    private RelativeLayout mContent;

    private RelativeLayout mContentLoading;

    private DiscoverGamesContract.Presenter mPresenter;

    private GameCollectionAdapter mPopularGamesAdapter;

    private GameCollectionAdapter mMostAnticipatedGamesAdapter;

    private GameCollectionAdapter mUpcomingGamesAdapter;

    private OnBottomReachedListener mPopularGamesScrollEndListener = new OnBottomReachedListener() {
        @Override
        public void onBottomReached() {
            mPresenter.loadPopularGames(false);
        }
    };

    private OnBottomReachedListener mMostAnticipatedGamesScrollEndListener = new OnBottomReachedListener() {
        @Override
        public void onBottomReached() {
            mPresenter.loadMostAnticipatedGames(false);
        }
    };

    private OnBottomReachedListener mUpcomingGamesScrollEndListener = new OnBottomReachedListener() {
        @Override
        public void onBottomReached() {
            mPresenter.loadUpcomingGames(false);
        }
    };



    public DiscoverGamesFragment() {

    }

    public static DiscoverGamesFragment newInstance() {
        return new DiscoverGamesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPopularGamesAdapter = new GameCollectionAdapter(getContext(), new ArrayList<Game>(0), this,
                R.layout.item_game_card, mPopularGamesScrollEndListener);
        mMostAnticipatedGamesAdapter = new GameCollectionAdapter(getContext(), new ArrayList<Game>(0),
                this, R.layout.item_game_card, mMostAnticipatedGamesScrollEndListener);
        mUpcomingGamesAdapter = new GameCollectionAdapter(getContext(), new ArrayList<Game>(0), this,
                R.layout.item_game_card, mUpcomingGamesScrollEndListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_games, container, false);

        GridLayoutManager popularGamesLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager mostAnticipatedGamesLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager upcomingGamesLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);

        RecyclerView popularGamesRecyclerView = (RecyclerView) view.findViewById(R.id.popular_games_list);
        RecyclerView mostAnticipatedGamesRecyclerView = (RecyclerView) view.findViewById(R.id.most_anticipated_games_list);
        RecyclerView upcomingGamesRecyclerView = (RecyclerView) view.findViewById(R.id.upcoming_games_list);
        mContent = (RelativeLayout) view.findViewById(R.id.content);
        mContentLoading = (RelativeLayout) view.findViewById(R.id.content_loading_progress);


        // TODO: check https://github.com/rubensousa/RecyclerViewSnap
        SnapHelper helper = new LinearSnapHelper();

        helper.attachToRecyclerView(popularGamesRecyclerView);
        helper.attachToRecyclerView(mostAnticipatedGamesRecyclerView);
        helper.attachToRecyclerView(upcomingGamesRecyclerView);


        popularGamesRecyclerView.setLayoutManager(popularGamesLayoutManager);
        popularGamesRecyclerView.setAdapter(mPopularGamesAdapter);

        mostAnticipatedGamesRecyclerView.setLayoutManager(mostAnticipatedGamesLayoutManager);
        mostAnticipatedGamesRecyclerView.setAdapter(mMostAnticipatedGamesAdapter);

        upcomingGamesRecyclerView.setLayoutManager(upcomingGamesLayoutManager);
        upcomingGamesRecyclerView.setAdapter(mUpcomingGamesAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onGameClickListener(Game clickedGame) {
        mPresenter.openGameDetail(clickedGame);
    }

    @Override
    public void setPresenter(DiscoverGamesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        toggleContentVisibility(active);
    }

    private void toggleContentVisibility(boolean visible) {
        if (!visible) {
            mContentLoading.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
        } else {
            mContent.setVisibility(View.GONE);
            mContentLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showPopularGames(List<Game> games) {
        mPopularGamesAdapter.replaceData(games);
    }

    @Override
    public void showMostAnticipatedGames(List<Game> games) {
        mMostAnticipatedGamesAdapter.replaceData(games);
    }

    @Override
    public void showUpcomingGames(List<Game> games) {
        mUpcomingGamesAdapter.replaceData(games);
    }

    @Override
    public void showLoadingPopularGamesError() {

    }

    @Override
    public void showLoadingMostlyAnticipatedGamesError() {

    }

    @Override
    public void showLoadingUpcomingGamesError() {

    }

    @Override
    public void showGameDetailUi(String gameId) {
        Intent intent = new Intent(getContext(), GameDetailActivity.class);
        intent.putExtra(GameDetailActivity.EXTRA_GAME_ID, gameId);
        startActivity(intent);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
