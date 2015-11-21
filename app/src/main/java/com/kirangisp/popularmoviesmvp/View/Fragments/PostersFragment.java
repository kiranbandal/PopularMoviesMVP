package com.kirangisp.popularmoviesmvp.View.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.kirangisp.popularmoviesmvp.Presenter.IPosterFragmentPresenter;
import com.kirangisp.popularmoviesmvp.Presenter.IPosterFragmentView;
import com.kirangisp.popularmoviesmvp.Presenter.PosterFragmentPresenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.popularmoviesmvp.View.Activities.MovieDetailsActivity;
import com.kirangisp.popularmoviesmvp.View.MovieJSONParser;


/**
 * Contains the gridView that displays the movie posters. This fragment will be added to the
 * FrameLayout of Posters Activity
 */
public class PostersFragment extends Fragment implements IPosterFragmentView, AdapterView.OnItemClickListener {

    private final static String POSTERS_GRIDVIEW_FRAGMENT_LOG_TAG = "Posters Grid Fragment";
    View fragmentRootView;

    //reference to Presenter
    IPosterFragmentPresenter presenter = null;

    //region on Create
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //endregion

    //region On Create View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try {

            fragmentRootView = inflater.inflate(R.layout.layout_posters_fragment, container, false);
        } catch (android.view.InflateException ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOG_TAG, errMsg);
        }

        //find the GridView and subscribe to it's on item click listener
        GridView posterGridView = (GridView) fragmentRootView.findViewById(R.id.moviePostersGrdView);
        posterGridView.setOnItemClickListener(this);
        return fragmentRootView;
    }
    //endregion

    //region on Activity Created
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter == null) {
            presenter = new PosterFragmentPresenterImpl(this);
            presenter.setGlobalDeviceImgeViewProps(getActivity());
        }

        //meaning, fragment is being created due to device orientation change
        if (savedInstanceState != null &&
                savedInstanceState.getParcelableArrayList(CommonGlobalObjects.getMoviePostersDataKey()) != null) {

        }

        //Read the URL value from Global Constants
        String getPopularMoviesURL = CommonGlobalObjects.getPopularMoviesURL() + CommonGlobalObjects.getMovieApiKey();

        //call the method on the presenter to start the process to get the popular movie data
        presenter.getPopularMoviesData(getPopularMoviesURL);

    }


    //region called from the presenter to deliver the popular movies data in json
    @Override
    public void deliverPopularMoviesDataInJson(String rawJSON) {

        //get the popular movies json from the incoming parameter
        String pplrMoviesJson = rawJSON;

        //create instance of response handler and depending in request type, call the appropriate method
        MovieResponseHandler responseHandler = new MovieResponseHandler();

        //save the ArrayList in global property so that it can be accessed on the reqd fragment
        CommonGlobalObjects.setMoviePosterInfoArrayLst(new MovieJSONParser(pplrMoviesJson).parseFavoriteMoviesJSON());
        responseHandler.displayMoviePosters(CommonGlobalObjects.getMoviePosterInfoArrayLst(),
                getActivity(), R.id.moviePostersGrdView);
    }


    //endregion

    //region Poster GridView Click Handler
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //get the image view that was clicked by the user
        ImageView clickedPoster = (ImageView) view;
        String clickedMovieId = clickedPoster.getTag().toString();

        if (clickedMovieId == null) return;

        //create instance of Detail Info Activity and star it
        Intent detailActivityIntent = new Intent(getActivity(),
                MovieDetailsActivity.class);

        try {

            detailActivityIntent.putExtra(CommonGlobalObjects.getSelectedMovieIdLiteral(), clickedMovieId);
            startActivity(detailActivityIntent);
        } catch (ActivityNotFoundException ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Activity Not Found Exception",
                    "moviePostersGrdView.setOnItemClickListener()", ex.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOG_TAG, errMsg);
        }
        catch (Exception ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception",
                    "moviePostersGrdView.setOnItemClickListener()", ex.getMessage());
            Log.e(POSTERS_GRIDVIEW_FRAGMENT_LOG_TAG, errMsg);
        }

    }
    //endregion
}
