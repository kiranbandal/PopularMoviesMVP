package com.kirangisp.commonandroidobjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The moviedb api network call returns the json string.
 * After parsing that string, we save movie poster data in ArrayList
 * of instances of this class. This class also implements the Parcelable interface so that
 * the ArrayList of this class instances can ve saved in the bundle.
 */
public class MoviePosterData implements Parcelable {

    private String mMoviePosterPath;
    private String mMovieIdInAPI;

    //region Constructors
    public MoviePosterData(String moviePosterPathInput, String movieIdInAPIInput) {
        mMoviePosterPath = moviePosterPathInput;
        mMovieIdInAPI = movieIdInAPIInput;
    }

    //Constructor needed for Parcel CREATOR
    MoviePosterData(Parcel input){
        mMovieIdInAPI = input.readString();
        mMoviePosterPath = input.readString();
    }
    //endregion

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }


    public String getMovieIDInAPI() {
        return mMovieIdInAPI;
    }

    //region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //Write movie ID and it's poster path to the parcel
        dest.writeString(mMovieIdInAPI);
        dest.writeString(mMoviePosterPath);

    }

    public static final Parcelable.Creator<MoviePosterData> CREATOR
            = new Parcelable.Creator<MoviePosterData>() {

        public MoviePosterData createFromParcel(Parcel in) {
            return new MoviePosterData(in);
        }

        public MoviePosterData[] newArray(int size) {
            return new MoviePosterData[size];
        }
    };
    //endregion
}
