package com.raju.joel.gamerinside.gamedetail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raju.joel.gamerinside.R;

import java.util.List;

/**
 * Created by Joel on 06-Oct-17.
 */

abstract class GameImageVideoGalleryAdapter extends RecyclerView.Adapter<GameImageVideoGalleryAdapter.GameImageVideoVH> {


    private List<?> mGalleryList;

    private int mLayoutId;

    GameImageVideoGalleryAdapter() {

    }

    GameImageVideoGalleryAdapter(List<?> galleryList, int layoutId) {
        mLayoutId = layoutId;
        setGalleryList(galleryList);
    }

    private void setGalleryList(List<?> galleryList) {
        mGalleryList = galleryList;
    }

    @Override
    public GameImageVideoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutId, parent, false);
        return new GameImageVideoVH(itemView);
    }

    public void replaceData(List<?> galleryList) {
        setGalleryList(galleryList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGalleryList.size();
    }

    public List<?> getGalleryList() {
        return mGalleryList;
    }

    protected class GameImageVideoVH extends RecyclerView.ViewHolder {

        private CardView mGalleryItemCard;
        private TextView mGalleryItemText;
        private ImageView mGalleryItemImage;

        GameImageVideoVH(View itemView) {
            super(itemView);
            mGalleryItemCard  = (CardView)  itemView.findViewById(R.id.game_gallery_item_card);
            mGalleryItemImage = (ImageView) itemView.findViewById(R.id.game_gallery_item_cover);
            mGalleryItemText  = (TextView)  itemView.findViewById(R.id.game_gallery_item_description);
        }

        public CardView getGalleryItemCard() {
            return mGalleryItemCard;
        }

        public TextView getGalleryItemText() {
            return mGalleryItemText;
        }

        public ImageView getGalleryItemImage() {
            return mGalleryItemImage;
        }
    }
}
