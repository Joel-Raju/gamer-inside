package com.raju.joel.gamerinside.gamedetail;

import android.content.Context;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joel on 17-Oct-17.
 */

public class VideoGalleryAdapter extends GameImageVideoGalleryAdapter {

    private Context mContext;

    VideoGalleryAdapter(Context context, List<Game.Video> videoList) {
        super(videoList);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(GameImageVideoVH holder, int position) {
        final Game.Video video = (Game.Video) getGalleryList().get(position);
//        Picasso.with(mContext)
//                .load(R.drawable.ic_chrome_reader_mode_black_24dp)
//                .into(holder.getGalleryItemImage());
    }
}
