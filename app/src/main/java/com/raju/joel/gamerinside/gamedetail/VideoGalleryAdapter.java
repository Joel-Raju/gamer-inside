package com.raju.joel.gamerinside.gamedetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joel on 17-Oct-17.
 */

public class VideoGalleryAdapter extends GameImageVideoGalleryAdapter {

    private Context mContext;

    private static String YOUTUBE_APP_URI = "vnd.youtube:";

    private static String YOUTUBE_WEB_URI = "http://www.youtube.com/watch?v=";


    VideoGalleryAdapter(Context context, List<Game.Video> videoList, int layoutId) {
        super(videoList, layoutId);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(GameImageVideoVH holder, int position) {
        final Game.Video video = (Game.Video) getGalleryList().get(position);
        Picasso.with(mContext)
                .load(R.drawable.placeholder_image)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.getGalleryItemImage());
        holder.getGalleryItemText().setText(video.getName());

        holder.getGalleryItemCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(YOUTUBE_APP_URI + video.getYoutubeVideoId()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(YOUTUBE_WEB_URI + video.getYoutubeVideoId()));

                try {
                    mContext.startActivity(appIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    mContext.startActivity(webIntent);
                }
            }
        });
    }
}
