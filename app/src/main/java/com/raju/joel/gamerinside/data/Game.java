package com.raju.joel.gamerinside.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raju.joel.gamerinside.data.source.Platform;

import java.util.Date;
import java.util.List;

public class Game {

    @SerializedName("id")
    @Expose
    String mId;

    @SerializedName("name")
    @Expose
    String mName;

    @SerializedName("summary")
    @Expose
    String mSummary;

    @SerializedName("storyline")
    @Expose
    String mStoryLine;

    @SerializedName("popularity")
    String mPopularity;

    @SerializedName("cover")
    @Expose
    CoverImage mCoverImage;

    @SerializedName("hypes")
    @Expose
    String mHypes;


    @SerializedName("total_rating")
    @Expose
    String mCriticsRating;

    @SerializedName("aggregated_rating")
    @Expose
    String mGamersRating;

    @SerializedName("first_release_date")
    @Expose
    String mInitialReleaseDate;

    @SerializedName("platforms")
    @Expose
    List<Platform> mPlatforms;

    @SerializedName("screenshots")
    @Expose
    List<ScreenShot> mScreenshots;

    @SerializedName("videos")
    @Expose
    List<Video> mVideos;

    @SerializedName("pegi")
    @Expose
    PEGI mPEGI;

    @SerializedName("esrb")
    @Expose
    ESRB mESRB;

    public static class CoverImage {
        @SerializedName("cloudinary_id")
        @Expose
        String mCloudId;

        public String getCloudId() {
            return mCloudId;
        }
    }

    public static class ScreenShot {
        @SerializedName("cloudinary_id")
        @Expose
        String mCloudId;

        public String getCloudId() {
            return mCloudId;
        }
    }

    public static class Video {
        @SerializedName("name")
        @Expose
        String mName;

        public String getName() {
            return mName;
        }

        @SerializedName("video_id")
        @Expose
        String mYoutubeVideoId;

        public String getYoutubeVideoId() {
            return mYoutubeVideoId;
        }
    }

    public static class PEGI {
        @SerializedName("rating")
        @Expose
        String mRating;

        @SerializedName("synopsis")
        @Expose
        String mSynopsis;

        public String getRating() {
            return mRating;
        }

        public String getSynopsis() {
            return mSynopsis;
        }
    }

    public static class ESRB {
        @SerializedName("rating")
        @Expose
        String mRating;

        @SerializedName("synopsis")
        @Expose
        String mSynopsis;

        public String getRating() {
            return mRating;
        }

        public String getSynopsis() {
            return mSynopsis;
        }
    }


    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getStoryLine() {
        return mStoryLine;
    }

    public String getCriticsRating() {
        return mCriticsRating;
    }

    public String getGamersRating() {
        return mGamersRating;
    }

    public CoverImage getCoverImage() {
        return mCoverImage;
    }

    public Date getInitialReleaseDate() {
        Date releaseDate = null;
        try {
            releaseDate = new Date(Long.parseLong(mInitialReleaseDate)) ;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return releaseDate;
    }

    public List<Platform> getPlatforms() {
        return mPlatforms;
    }

    public List<ScreenShot> getScreenshots() {
        return mScreenshots;
    }

    public List<Video> getVideos() {
        return mVideos;
    }
}
