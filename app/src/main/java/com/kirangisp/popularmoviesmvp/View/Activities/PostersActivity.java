package com.kirangisp.popularmoviesmvp.View.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.kirangisp.popularmoviesmvp.Presenter.IPostersActivityView;
import com.kirangisp.popularmoviesmvp.Presenter.PosterActivityPresenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.popularmoviesmvp.View.Fragments.PostersFragment;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;

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
    //endregion
}
