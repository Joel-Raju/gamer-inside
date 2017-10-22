package com.raju.joel.gamerinside.gamecollection;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.discover.DiscoverGamesFragment;
import com.raju.joel.gamerinside.gamedetail.GameListener;
import com.raju.joel.gamerinside.ui.OnBottomReachedListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joel on 21-Sep-17.
 */

public class GameCollectionAdapter extends RecyclerView.Adapter<GameCollectionAdapter.DiscoverGameVH> {

    private static final String IMAGE_BASE_URL = "https://images.igdb.com/igdb/image/upload/t_cover_big/";

    private static final String IMAGE_FILE_EXTENSION = ".png";

    private Context mContext;

    private List<Game> mGameList;

    private GameListener mGameListener;

    private int mItemResource;

    private OnBottomReachedListener mOnBottomReachedListener;

    public GameCollectionAdapter(Context context, List<Game> gameList,
                                 GameListener listener, int resourceId,
                                 OnBottomReachedListener onBottomReachedListener) {
        mContext = context;
        setGameList(gameList);
        mGameListener = listener;
        mItemResource = resourceId;
        mOnBottomReachedListener = onBottomReachedListener;
    }

    @Override
    public DiscoverGameVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mItemResource, parent, false);
        return new DiscoverGameVH(itemView);
    }

    @Override
    public void onBindViewHolder(DiscoverGameVH holder, int position) {
        if (position == (mGameList.size()-1)) {
            mOnBottomReachedListener.onBottomReached();
        }
        final Game game = mGameList.get(position);
        String imageUrl = getGameCoverPath(game);

        holder.gameTitle.setText(game.getName());
        if (!imageUrl.isEmpty()) {
            Picasso.with(mContext)
                    .load(imageUrl)
                    .fit()
                    .into(holder.gameCover);
        }

        holder.gameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameListener.onGameClickListener(game);
            }
        });

        holder.gameTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameListener.onGameClickListener(game);
            }
        });

        holder.gameCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameListener.onGameClickListener(game);
            }
        });
    }

    public void replaceData(List<Game> games) {
        setGameList(games);
        notifyDataSetChanged();
    }

    public void setGameList(List<Game> mGameList) {
        this.mGameList = mGameList;
    }

    private static String getGameCoverPath(Game game) {
        if (game == null || game.getCoverImage() == null || game.getCoverImage().getCloudId() == null) {
            return "";
        }
        return IMAGE_BASE_URL + game.getCoverImage().getCloudId() + IMAGE_FILE_EXTENSION;
    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }

    public class DiscoverGameVH extends RecyclerView.ViewHolder {

        private CardView gameCard;
        private ImageView gameCover;
        private TextView gameTitle;


        public DiscoverGameVH(View itemView) {
            super(itemView);
            gameCard  = (CardView) itemView.findViewById(R.id.game_card_view);
            gameCover = (ImageView) itemView.findViewById(R.id.game_card_item_cover);
            gameTitle = (TextView)  itemView.findViewById(R.id.game_card_item_title);
        }
    }
}
