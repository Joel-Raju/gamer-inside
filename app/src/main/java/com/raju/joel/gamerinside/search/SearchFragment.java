package com.raju.joel.gamerinside.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.gamecollection.GameCollectionAdapter;
import com.raju.joel.gamerinside.gamedetail.GameDetailActivity;
import com.raju.joel.gamerinside.gamedetail.GameListener;
import com.raju.joel.gamerinside.ui.OnBottomReachedListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchContract.View,
        SearchView.OnQueryTextListener, OnBottomReachedListener {

    private SearchContract.Presenter mPresenter;

    private GameCollectionAdapter mAdapter;

    private Handler mHandler;

    private Runnable mRunnable;

    private RelativeLayout mContent;

    private RelativeLayout mContentLoading;

    private GameListener mGameListener = new GameListener() {
        @Override
        public void onGameClickListener(Game clickedGame) {
            mPresenter.openGameDetail(clickedGame);
        }
    };

    private static int SEARCH_DEBOUNCE_TIME_IN_MS = 1000;

    private static int MIN_KEYWORD_LENGTH = 3;

    private static int ITEM_SPAN_COUNT = 3;

    private String mSearchQuery;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mAdapter = new GameCollectionAdapter(getContext(), new ArrayList<Game>(0),
                mGameListener, R.layout.item_game_card_grid, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        SearchView gameSearchView = (SearchView) rootView.findViewById(R.id.game_search);
        RecyclerView resultsView = (RecyclerView) rootView.findViewById(R.id.search_results);
        mContent = (RelativeLayout) rootView.findViewById(R.id.content);
        mContentLoading = (RelativeLayout) rootView.findViewById(R.id.content_loading_progress);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), ITEM_SPAN_COUNT);

        resultsView.setLayoutManager(layoutManager);
        resultsView.setItemAnimator(new DefaultItemAnimator());
        resultsView.setAdapter(mAdapter);
        gameSearchView.setOnQueryTextListener(this);
        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchQuery = query;
        performSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mSearchQuery = query;
        performSearch(query);
        return false;
    }

    @Override
    public void onBottomReached() {
        mPresenter.loadSearchResults(mSearchQuery);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void performSearch(final String searchString) {
        if (searchString.length() > MIN_KEYWORD_LENGTH) {
            mPresenter.clearSearchedResults();
            mHandler.removeCallbacks(mRunnable);
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    mPresenter.loadSearchResults(searchString);
                }
            };
            mHandler.postDelayed(mRunnable, SEARCH_DEBOUNCE_TIME_IN_MS);
        }
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
    public void showSearchResults(List<Game> games) {
        mAdapter.replaceData(games);
    }

    @Override
    public void showSearchError() {

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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
