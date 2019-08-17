package com.glriverside.xgqin.ggnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class News {


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

    public String getPicUrl() {
        return mPicUrl;
    }

    public void setPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    public String getContentUrl() {
        return mContentUrl;
    }

    public void setContentUrl(String mContentUrl) {
        this.mContentUrl = mContentUrl;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean mRead) {
        this.mRead = mRead;
    }

    public boolean isDeleted() {
        return mDeleted;
    }

    public void setDeleted(boolean mDeleted) {
        this.mDeleted = mDeleted;
    }

    public Integer getId() {
        return mId;
    }

    public boolean isLiked() {
        return mLiked;
    }

    public void setLiked(boolean mLiked) {
        this.mLiked = mLiked;
    }

    public int getLikedCount() {
        return mLikedCount;
    }

    public void liked() {
        this.mLikedCount++;
    }

    public void unliked() {
        this.mLikedCount--;
    }

    public void setLikedCount(int mLikedCount) {
        this.mLikedCount = mLikedCount;
    }

    public String getDate() {
        return mPublishTime;
    }

    public News() {
        Random random = new Random();
        this.mLikedCount = random.nextInt(1000);
    }

    @Expose(serialize = false, deserialize = false)
    private Integer mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mSource;

    @SerializedName("picUrl")
    private String mPicUrl;

    @SerializedName("url")
    private String mContentUrl;

    @SerializedName("ctime")
    private String mPublishTime;

    @Expose(serialize = false, deserialize = false)
    private boolean mRead = false;

    @Expose(serialize = false, deserialize = false)
    private boolean mDeleted = false ;

    @Expose(serialize = false, deserialize = false)
    private boolean mLiked = false;

    @Expose(serialize = false, deserialize = false)
    private int mLikedCount = 0;

}
