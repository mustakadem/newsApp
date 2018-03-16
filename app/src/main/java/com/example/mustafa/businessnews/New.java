package com.example.mustafa.businessnews;

import java.io.Serializable;




/**
 * Created by mustafa on 15/03/2018.
 */

public class New implements Serializable {


    private static final long serialVersionUID = -2763122753290886624L;
    private String mTitle;
    private String mUrl;
    private String mAuthor;
    private String mImage;
    private String mDescription;

    public New(String mTitle,  String mAuthor, String mImage, String mDescription) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mImage = mImage;
        this.mDescription = mDescription;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public String toString() {
        return "New{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
