package com.kirangisp.commonandroidobjects;

import android.os.Parcel;
import android.os.Parcelable;

import com.kirangisp.commonandroidobjects.MoviePosterdata;

/**
 * This class is used on MovieDetailInfoFragments to show the details of a selected movie. This class implements Parcelable
 * so that instance of this class can be saved into the Bundle.
 */
public class SelectedMovieData  implements Parcelable {

    private String title;
    private String releaseYear;
    private String vote;
    private String synopsis;
    private String moviePosterPath;


    public SelectedMovieData(String moviePosterPathInput,
                                    String movieTitle,
                                    String movieReleaseYear,
                                    String movieVote,
                                    String movieSynopsis) {

        this.moviePosterPath = moviePosterPathInput;
        this.title = movieTitle;
        this.releaseYear = movieReleaseYear;
        this.vote = movieVote;
        this.synopsis = movieSynopsis;
    }

    //Constructor needed for Parcelable
    SelectedMovieData(Parcel input) {

        title = input.readString();
        releaseYear = input.readString();
        vote = input.readString();
        synopsis = input.readString();
        moviePosterPath = input.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(releaseYear);
        dest.writeString(vote);
        dest.writeString(synopsis);
        dest.writeString(moviePosterPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SelectedMovieData> CREATOR = new Creator<SelectedMovieData>() {
        @Override
        public SelectedMovieData createFromParcel(Parcel in) {
            return new SelectedMovieData(in);
        }

        @Override
        public SelectedMovieData[] newArray(int size) {
            return new SelectedMovieData[size];
        }
    };

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getVote() {
        return vote;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
