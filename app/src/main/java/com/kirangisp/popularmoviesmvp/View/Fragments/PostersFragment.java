package com.kirangisp.popularmoviesmvp.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirangisp.popularmoviesmvp.Presenter.IPosterFragmentPresenter;
import com.kirangisp.popularmoviesmvp.Presenter.IPosterFragmentView;
import com.kirangisp.popularmoviesmvp.Presenter.PosterFragmentPresenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;


/**
 * Contains the gridView that displays the movie posters. This fragment will be added to the
 * FrameLayout of Posters Activity
 */
public class PostersFragment extends Fragment implements IPosterFragmentView {

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
                getActivity(),R.id.moviePostersGrdView);

    }
    //endregion
}
