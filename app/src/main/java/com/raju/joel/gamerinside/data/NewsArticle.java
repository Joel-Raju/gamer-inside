package com.raju.joel.gamerinside.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joel on 03-Sep-17.
 */

public class NewsArticle {

    @SerializedName("id")
    @Expose
    String mId;

    @SerializedName("title")
    @Expose
    String mTitle;

    @SerializedName("summary")
    @Expose
    String mSummary;

    @SerializedName("author")
    @Expose
    String mAuthor;

    @SerializedName("created_at")
    @Expose
    String mCreatedTimestamp;

    /**
     * High Res Image Url
     */
    @SerializedName("image")
    @Expose
    String mImageLarge;


    @SerializedName("pulse_image")
    @Expose
    PulseImage mPulseImage;

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getCreatedTimestamp() {
        return mCreatedTimestamp;
    }

    public String getImageLarge() {
        return mImageLarge;
    }

    public PulseImage getPulseImage() {
        return mPulseImage;
    }

    public static class PulseImage {

        @SerializedName("url")
        String mUrl;

        @SerializedName("cloudinary_id")
        String mCloudId;

        public String getUrl() {
            return mUrl;
        }

        public String getCloudId() {
            return mCloudId;
        }
    }
}
