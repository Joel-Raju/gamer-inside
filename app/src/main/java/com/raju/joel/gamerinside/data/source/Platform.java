package com.raju.joel.gamerinside.data.source;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joel on 19-Oct-17.
 */

public class Platform {

    @SerializedName("id")
    @Expose
    String mId;

    @SerializedName("name")
    @Expose
    String mName;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
