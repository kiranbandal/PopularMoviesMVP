package com.kirangisp.commonandroidobjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 20-Nov-15.
 */
public class MoviePosterdata implements Parcelable{

    String moviePosterPath;
    String movieIdInAPI;

    public MoviePosterdata(String moviePosterPathInput, String movieIdInAPIInput) {
        this.moviePosterPath = moviePosterPathInput;
        this.movieIdInAPI = movieIdInAPIInput;
    }

    //Constructor needed for Parcel CREATOR
    MoviePosterdata(Parcel input){
        movieIdInAPI = input.readString();
        moviePosterPath = input.readString();
    }

    public String getMoviePosterPath() {

        return moviePosterPath;
    }


    public String getMovieIDInAPI() {

        return movieIdInAPI;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //Write movie ID and it's poster path to the parcel
        dest.writeString(movieIdInAPI);
        dest.writeString(moviePosterPath);

    }

    public static final Parcelable.Creator<MoviePosterdata> CREATOR
            = new Parcelable.Creator<MoviePosterdata>() {

        public MoviePosterdata createFromParcel(Parcel in) {
            return new MoviePosterdata(in);
        }

        public MoviePosterdata[] newArray(int size) {
            return new MoviePosterdata[size];
        }
    };
}
