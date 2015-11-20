package com.kirangisp.commonandroidobjects;



import java.util.ArrayList;

public class CommonGlobalObjects {

    //region Posters GridView Fragment Tag value
    public final static String GRID_VIEW_FRAGMENT_TAG = "GRIDVIEW FRAGMENT";

    public static String getGridViewFragmentTag() {
        return GRID_VIEW_FRAGMENT_TAG;
    }
    //endregion

    //region Construct Error Message
    public static String constructErrorMsg(String errType, String functionName, String errMsg) {

        String returnErrorMessage = "The " + errType + " occurred in the function, " +
                functionName + ", Error message : " + errMsg;
        return returnErrorMessage;
    }
    //endregion

    //region Get all movies url - first part
    public final static String GET_ALL_MOVIES_URL_FIRST_PART = "http://api.themoviedb.org/3/discover/movie?&api_key=";

    public static String getPopularMoviesURL() {
        return GET_ALL_MOVIES_URL_FIRST_PART;
    }
    //endregion

    //region Movies DB API Key
    public final static String MOVIE_API_KEY = "5254a84a6c18f9e3cbec007290ea297c";

    public static String getMovieApiKey() {
        return MOVIE_API_KEY;
    }
    //endregion

    //region Json Tag name in popular movies json
    public final static String JSON_RESULT_TAG_NAME = "results";

    public static String getJsonResultsTagName() {
        return JSON_RESULT_TAG_NAME;
    }
    //endregion

    //region KEY used to save the movie posters data into bundle
    private static final String MOVIE_POSTERS_DATA_KEY = "Movie Posters Data";

    public static String getMoviePostersDataKey() {
        return MOVIE_POSTERS_DATA_KEY;
    }
    //endregion

    //region Set Image this to be queried from the movie db api
    //Movie poster image size, available options are
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original". Note: doulbe slashes at the end are must
    public static String MOVIE_POSTER_IMG_SIZE;

    public static void setMoviePosterImgSize(String moviePosterImgSize) {
        MOVIE_POSTER_IMG_SIZE = moviePosterImgSize;
    }

    public static String getMoviePosterImageSize() {
        return MOVIE_POSTER_IMG_SIZE;
    }
    //endregion

    //region Base URL to get the full path of the movie poster,
    public final static String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/";

    public static String getMoviePosterBaseURL() {
        return MOVIE_POSTER_BASE_URL;
    }
    //endregion

    //region ArrayList to be saved in Bundle in OnSavedInstance method on MoviePosters frgament
    private static ArrayList<MoviePosterdata> moviePosterInfoArrayLst;

    public static ArrayList<MoviePosterdata> getMoviePosterInfoArrayLst() {
        return moviePosterInfoArrayLst;
    }

    public static void setMoviePosterInfoArrayLst(ArrayList<MoviePosterdata> moviePosterInfoArrayLst) {
        CommonGlobalObjects.moviePosterInfoArrayLst = moviePosterInfoArrayLst;
    }
    //endregion

}
