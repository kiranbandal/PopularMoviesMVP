package com.kirangisp.popularmoviesmvp.View.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.popularmoviesmvp.Presenter.Activities.IMovieDetailsActivityPresenter;
import com.kirangisp.popularmoviesmvp.Presenter.Activities.IMovieDetailsActivityView;
import com.kirangisp.popularmoviesmvp.Presenter.Activities.MovieDetailsActivityPresenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.popularmoviesmvp.View.Fragments.MovieDetailsFragment;
import com.kirangisp.popularmoviesmvp.View.Fragments.PostersFragment;

public class MovieDetailsActivity extends AppCompatActivity implements IMovieDetailsActivityView {

    private final static String MOVIE_DETAILS_FRAGMENT_LOG_TAG = "Movie Details";

    //reference to the presenter
    IMovieDetailsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Add the Movie Details Fragment here
        addDetailsFragment(savedInstanceState);

        //set Presenter object
        presenter = new MovieDetailsActivityPresenterImpl(this);
    }

    public void addDetailsFragment(Bundle instce) {

        try {

            // if the activity is not getting created for the first time, then do not do anything
            if (instce != null) {
                return;
            }

            //create fragment instance from the library and add it to the activity
            MovieDetailsFragment detailsFrag = new MovieDetailsFragment();

            getSupportFragmentManager().beginTransaction().
                    add(R.id.MovieDetailFragContainer,
                            detailsFrag, CommonGlobalObjects.getGridViewFragmentTag()).commit();

            return;
        } catch (Fragment.InstantiationException ex) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Fragment Instantiation Exception",
                    "addPostersFragment()",
                    ex.getMessage());

            Log.e(MOVIE_DETAILS_FRAGMENT_LOG_TAG, errMsg);
            return;

        } catch (Exception ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "onCreate()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAGMENT_LOG_TAG, errMsg);
            return;
        }


    }

}
