package com.kirangisp.popularmoviesmvp.View.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kirangisp.popularmoviesmvp.Presenter.IPostersActivityView;
import com.kirangisp.popularmoviesmvp.Presenter.PosterActivityPresenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.popularmoviesmvp.View.Fragments.MovieResponseHandler;
import com.kirangisp.popularmoviesmvp.View.Fragments.PostersFragment;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.popularmoviesmvp.View.MovieJSONParser;

public class PostersActivity extends AppCompatActivity implements IPostersActivityView {

    //Tag For writing Log
    private final String POSTER_ACTIVITY_LOG_TAG = "Movie Posters Activity";

    //Presenter object
    PosterActivityPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_posters_activity);

        //create Presenter and check if device has internet connection. If not, then exit.
        presenter = new PosterActivityPresenterImpl(this);

        if (!presenter.isDeviceConnectedToInternet(this, POSTER_ACTIVITY_LOG_TAG)) {

            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Add the movie posters fragment
        addPostersFragment(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mnuInflater = getMenuInflater();

        try {
            mnuInflater.inflate(R.menu.menu_posters_activity, menu);
        } catch (android.view.InflateException ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //get Id of the clicked menu item
        int id = item.getItemId();

        if (id == R.id.action_sort_most_popular){

              /*query the movie db api to get the movie data sorted on most popular
            * Construct the url by reading global constants file
            * Sample query : http://api.themoviedb.org/3/discover/movie?&api_key=KEY_HERE&sort_by=popularity.desc
            * */

            String sortMoviesOnPopularityURL = CommonGlobalObjects.getPopularMoviesURL()
                    + CommonGlobalObjects.getMovieApiKey()
                    + CommonGlobalObjects.getSortKeyWord()
                    + CommonGlobalObjects.getPopularityFieldName();
            presenter.getMoviesJson(sortMoviesOnPopularityURL);

        }

        else if(id == R.id.action_sort_highest_rated){

            //query the movie db api to get the movie data sorted on highest rating
            String sortMoviesOnHighestVotingUrl = CommonGlobalObjects.getPopularMoviesURL()
                    + CommonGlobalObjects.getMovieApiKey()
                    + CommonGlobalObjects.getSortKeyWord()
                    + CommonGlobalObjects.getHighestRatingFieldName();

            presenter.getMoviesJson(sortMoviesOnHighestVotingUrl);
        }


        return true;
    }

    //region IPostersActivityView Implementation
    @Override
    public boolean addPostersFragment(Bundle instce) {

        // if the activity is not getting created for the first time, then do not do anything
        if (instce != null) {
            return false;
        }

        // Check that the activity has the container to hold the fragment
        if (findViewById(R.id.container_for_posters_fragment) == null) {
            return false;
        }


        try {

            //create fragment instance from the library and add it to the activity
            PostersFragment postersGridViewFragment = new PostersFragment();

            getSupportFragmentManager().beginTransaction().
                    add(R.id.container_for_posters_fragment,
                            postersGridViewFragment, CommonGlobalObjects.getGridViewFragmentTag()).commit();

            return true;
        } catch (Fragment.InstantiationException ex) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Fragment Instantiation Exception",
                    "addPostersFragment()",
                    ex.getMessage());

            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
            return false;

        } catch (Exception ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "onCreate()", ex.getMessage());
            Log.e(POSTER_ACTIVITY_LOG_TAG, errMsg);
            return false;
        }


    }

    @Override
    public void deleiverSortedMoviesJson(String sortedMoviesData) {

        String sortedMovieResponse;
        sortedMovieResponse= sortedMoviesData;

        //create instance of response handler and depending in request type, call the appropriate method
        MovieResponseHandler responseHandler = new MovieResponseHandler();

        //save the ArrayList in global property so that it can be accessed on the reqd fragment
        CommonGlobalObjects.setMoviePosterInfoArrayLst(new MovieJSONParser(sortedMovieResponse).parseFavoriteMoviesJSON());
        responseHandler.displayMoviePosters(CommonGlobalObjects.getMoviePosterInfoArrayLst(),
                PostersActivity.this,R.id.moviePostersGrdView);
    }
    //endregion
}
