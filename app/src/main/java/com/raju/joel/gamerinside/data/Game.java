package com.raju.joel.gamerinside.data;


import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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




    public static class screenShot {

    }


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }
}
