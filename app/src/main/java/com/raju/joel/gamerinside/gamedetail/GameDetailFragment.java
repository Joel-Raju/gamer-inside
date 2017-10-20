package com.raju.joel.gamerinside.gamedetail;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.source.Platform;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GameDetailFragment extends Fragment implements GameDetailContract.View {

    private GameDetailContract.Presenter mPresenter;

    private ImageView gameHeaderImage;

    private Toolbar gameTitleToolbar;

    private TextView gameSummary;

    private TextView gameStoryLine;

    private ProgressBar mGamerRating;

    private ProgressBar mCriticRating;

    private TextView mCriticsRatingValue;

    private TextView mGamerRatingValue;

    private TextView mGameTitle;

    private TextView mGamePlatforms;

    private TextView mInitialReleaseDate;

    private RecyclerView gameImageGalleryView;

    private RecyclerView gameVideoGalleryView;

    private RelativeLayout mContent;

    private RelativeLayout mContentLoading;

    private ImageGalleryAdapter mImageGalleryAdapter;

    private GameImageVideoGalleryAdapter mVideoGalleryAdapter;

    private static final String IMAGE_BASE_URL = "https://images.igdb.com/igdb/image/upload";

    private static final String IMAGE_HEADER_SIZE = "/t_screenshot_big/";

    private static final String IMAGE_FILE_EXTENSION = ".png";

    public GameDetailFragment() {
        // Required empty public constructor
    }

    public static GameDetailFragment newInstance(@NonNull String gameId) {
        GameDetailFragment fragment = new GameDetailFragment();
        Bundle args = new Bundle();
        args.putString(GameDetailActivity.EXTRA_GAME_ID, gameId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageGalleryAdapter = new ImageGalleryAdapter(getContext(), new ArrayList<Game.ScreenShot>(0));
        mVideoGalleryAdapter = new VideoGalleryAdapter(getContext(), new ArrayList<Game.Video>(0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        GridLayoutManager imageGalleryLayoutManager = new GridLayoutManager(getContext(), 1,
                GridLayoutManager.HORIZONTAL, false);

        GridLayoutManager videoGalleryLayoutManager = new GridLayoutManager(getContext(), 1,
                GridLayoutManager.HORIZONTAL, false);

        mContent = (RelativeLayout) view.findViewById(R.id.content);
        mContentLoading = (RelativeLayout) view.findViewById(R.id.content_loading_progress);
        mGameTitle = (TextView) view.findViewById(R.id.game_title);
        mInitialReleaseDate = (TextView) view.findViewById(R.id.game_release_date);
        gameHeaderImage = (ImageView) view.findViewById(R.id.game_header_image);
        gameTitleToolbar = (Toolbar) view.findViewById(R.id.game_detail_toolbar);
        gameSummary = (TextView) view.findViewById(R.id.game_summary);
        gameStoryLine = (TextView) view.findViewById(R.id.game_storyline);
        mGamePlatforms = (TextView) view.findViewById(R.id.game_platforms);
        mGamerRating = (ProgressBar) view.findViewById(R.id.game_gamer_rating);
        mCriticRating = (ProgressBar) view.findViewById(R.id.game_critic_rating);
        mCriticsRatingValue = (TextView) view.findViewById(R.id.game_critic_rating_value);
        mGamerRatingValue = (TextView) view.findViewById(R.id.game_gamer_rating_value);
        gameImageGalleryView = (RecyclerView) view.findViewById(R.id.game_image_gallery);
        gameVideoGalleryView = (RecyclerView) view.findViewById(R.id.game_video_gallery);

        gameImageGalleryView.setLayoutManager(imageGalleryLayoutManager);
        gameVideoGalleryView.setLayoutManager(videoGalleryLayoutManager);
        gameImageGalleryView.setAdapter(mImageGalleryAdapter);
        gameVideoGalleryView.setAdapter(mVideoGalleryAdapter);

        return view;
    }

    @Override
    public void setPresenter(GameDetailContract.Presenter presenter) {
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
    public void showGameDetail(Game game) {
        mGameTitle.setText(game.getName());
        gameSummary.setText(game.getSummary());
        gameStoryLine.setText(game.getStoryLine());

        if (game.getCoverImage() != null && game.getCoverImage().getCloudId() != null) {
            Picasso.with(getContext())
                    .load(getGameHeaderImage(game.getCoverImage().getCloudId()))
                    .into(gameHeaderImage);
        }

        if (game.getInitialReleaseDate() != null) {
            showInitialReleaseDate(game.getInitialReleaseDate());
        }

        if (game.getCriticsRating() != null && !game.getCriticsRating().isEmpty()) {
            showCriticsRating(game.getCriticsRating());
        }

        if (game.getGamersRating() != null && !game.getGamersRating().isEmpty()) {
            showGamerRating(game.getGamersRating());
        }

        if (game.getPlatforms() != null && game.getPlatforms().size() > 0) {
            showGamePlatforms(game.getPlatforms());
        }
    }

    private void showInitialReleaseDate(Date date) {
        String pattern = "dd MMM YYYY";
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(pattern);
        String formattedDate = dateFormat.format(date);
        mInitialReleaseDate.setText(formattedDate);

    }

    private void showGamePlatforms(List<Platform> platformList) {
        String platformString = "";
        for (Platform platform: platformList) {
            platformString += platform.getName() + ",";
        }
        if (platformString.charAt(platformString.length() -1 ) == ',') {
            platformString = platformString.substring(0, platformString.length() - 1);
        }
        mGamePlatforms.setText(platformString);
    }

    private void showCriticsRating(String rating) {
        int criticsRating = 0;
        try {
            criticsRating = Math.round(Float.parseFloat(rating));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mCriticsRatingValue.setText(String.valueOf(criticsRating));
        ObjectAnimator criticsRatingProgressAnimator = ObjectAnimator.ofInt(mCriticRating, "progress", 0, criticsRating);
        criticsRatingProgressAnimator.setDuration(500);
        criticsRatingProgressAnimator.setInterpolator(new DecelerateInterpolator());
        criticsRatingProgressAnimator.start();
    }

    private void showGamerRating(String rating) {
        int gamerRating = 0;
        try {
            gamerRating = Math.round(Float.parseFloat(rating));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mGamerRatingValue.setText(String.valueOf(gamerRating));
        ObjectAnimator gamerRatingProgressAnimator = ObjectAnimator.ofInt(mGamerRating, "progress", 0, gamerRating);
        gamerRatingProgressAnimator.setDuration(500);
        gamerRatingProgressAnimator.setInterpolator(new DecelerateInterpolator());
        gamerRatingProgressAnimator.start();
    }

    private String getGameHeaderImage(String cloudId) {
        return IMAGE_BASE_URL + IMAGE_HEADER_SIZE + cloudId + IMAGE_FILE_EXTENSION;
    }

    @Override
    public void showGameReviewCollection() {

    }

    @Override
    public void showGameImageGallery(List<Game.ScreenShot> screenShots) {
        mImageGalleryAdapter.replaceData(screenShots);
    }

    @Override
    public void showGameVideoGallery(List<Game.Video> videos) {
        mVideoGalleryAdapter.replaceData(videos);
    }

    @Override
    public void showLoadingGameDetailError() {

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
