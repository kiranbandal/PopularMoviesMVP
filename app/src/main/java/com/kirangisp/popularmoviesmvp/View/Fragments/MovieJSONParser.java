package com.kirangisp.popularmoviesmvp.View.Fragments;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.commonandroidobjects.MoviePosterdata;


/**
 * This class parses the raw json string retireved from network call to movie db api
 * and returns the result in required class instance or ArrayList of the class instances
 */
public class MovieJSONParser {

    public static final String JSON_PARSER_LOG_TAG = "Movie JSON Parser";
    private String mInputJSONString;

    //region Constructors
    //Empty Constructor
    public MovieJSONParser() {
    }

    //Constructor taking raw json as parameter
    public MovieJSONParser(String inputJSON) {

        this.mInputJSONString = inputJSON;
    }
    //endregion


//    public HelperModuleMovieDetails getMovieDetails() {
//
//        try {
//
//            JSONObject jsonObj = new JSONObject(this.mInputJSONString);
//
//            //we have flat json object here, so we can directly get the needed values from it
//            String relaseDate = jsonObj.getString("release_date");
//            String ttl = jsonObj.getString("original_title");
//            String vote = jsonObj.getString("vote_average");
//            String posterPath = jsonObj.getString("poster_path");
//            String synopsis = jsonObj.getString("overview");
//
//            return new HelperModuleMovieDetails(posterPath,
//                    null,
//                    ttl,
//                    relaseDate,
//                    vote,
//                    synopsis);
//
//        }
//        catch (JSONException ex) {
//            return null;
//
//        } catch (Exception e) {
//            return null;
//        }
//    }

    /*
     * This is the  method that gets called to parse the raw json (got from moviedb api call
     * while fetching data for all favorite movies
     *
     * */
    public ArrayList<MoviePosterdata> parseFavoriteMoviesJSON() {

        //Array List will be returned
        ArrayList<MoviePosterdata> favoriteMoviesDataArrayList = new ArrayList<MoviePosterdata>();

        try {

            //get entire json and get "results" array form it
            JSONObject entireJson = new JSONObject(this.mInputJSONString);
            JSONArray resultsJsonObjectsArray =
                    (JSONArray) entireJson.get(CommonGlobalObjects.getJsonResultsTagName());

            /**
             * //loop on all objects in "results" json,
             //starting from ""adult"" up to "vote_count"
             */
            for (int i = 0; i < resultsJsonObjectsArray.length(); i++) {

                //get each movie json
                JSONObject movieJson = resultsJsonObjectsArray.getJSONObject(i);

                //read values from json object and pass them in MovieInfo instance
                MoviePosterdata movieInfo = new MoviePosterdata
                        (movieJson.getString("poster_path"),
                                movieJson.getString("id"));

                //add our object
                favoriteMoviesDataArrayList.add(movieInfo);
            }

        } catch (JSONException jsonEx) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("JSON Exception",
                    "parseFavoriteMoviesJSON()", jsonEx.getMessage());
            Log.e(JSON_PARSER_LOG_TAG, errMsg);

        }
        catch (Exception ex) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception",
                    "parseFavoriteMoviesJSON()", ex.getMessage());
            Log.e(JSON_PARSER_LOG_TAG, errMsg);
        }

        finally {
            this.mInputJSONString = null;
            return favoriteMoviesDataArrayList;
        }

    }
}
