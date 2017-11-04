package com.raju.joel.gamerinside.gamedetail;

import android.content.Context;
import android.view.ViewGroup;

import com.raju.joel.gamerinside.R;
import com.raju.joel.gamerinside.data.Game;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joel on 15-Oct-17.
 */

public class ImageGalleryAdapter extends GameImageVideoGalleryAdapter {

    private static final String IMAGE_BASE_URL = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/";

    private static final String IMAGE_FILE_EXTENSION = ".png";

    private Context mContext;

    ImageGalleryAdapter(Context context, List<Game.ScreenShot> imageList, int layoutId) {
        super(imageList, layoutId);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(GameImageVideoVH holder, int position) {
        final Game.ScreenShot screenShot = (Game.ScreenShot) getGalleryList().get(position);
        String imageUrl = getScreenShotImagePath(screenShot.getCloudId());
        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(holder.getGalleryItemImage());
    }

    private static String getScreenShotImagePath(String cloudId) {
        return IMAGE_BASE_URL + cloudId + IMAGE_FILE_EXTENSION;
    }
}
