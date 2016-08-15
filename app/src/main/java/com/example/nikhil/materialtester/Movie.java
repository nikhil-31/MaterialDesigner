package com.example.nikhil.materialtester;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nikhil on 02-07-2016.
 */
public class Movie {
    private String mOriginalTitle;
    private String mPosterPath;
    private String mOverview;
    private Float mVoteAverage;
    private String mReleaseDate;
    private String mBackdrop;

    public Movie() {

    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;

    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;

    }

    public void setOverview(String overview) {
        mOverview = overview;

    }

    public void setVoteAverage(Float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setBackdrop(String backdrop) {
        mBackdrop = backdrop;

    }

    public String getBackdrop() {
        String back = "http://image.tmdb.org/t/p/w500/" + mBackdrop;
        return back;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;

    }

    public String getPosterPath() {
        String url = "http://image.tmdb.org/t/p/w185/" + mPosterPath;
        return url;
    }

    public String getOverview() {
        return mOverview;

    }

    public String getVoteAverage() {
        String rating = mVoteAverage + "/10";
        return rating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    @Override
    public String toString() {
        return "Title"+mOriginalTitle+
                "Poster Path"+mPosterPath+
                "overview"+mOverview;
    }
}
