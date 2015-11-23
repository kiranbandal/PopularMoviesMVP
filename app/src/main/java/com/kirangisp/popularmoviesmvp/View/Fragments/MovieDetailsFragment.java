package com.kirangisp.popularmoviesmvp.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.commonandroidobjects.SelectedMovieData;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IMovieDetailsFragPressenter;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IMovieDetaisFragmentView;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.MovieDetailsFragPressenterImpl;
import com.kirangisp.popularmoviesmvp.R;
import com.kirangisp.popularmoviesmvp.View.MovieJSONParser;

/**
 * The fragment that shows the selected movie details.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetaisFragmentView {

    private final static String MOVIE_DETAILS_FRAG_LOG_TAG = "Movie Details Fragment";
    View mFragmenRoot;

    //reference to the Presenter
    IMovieDetailsFragPressenter presenter;

    //region On Create
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //crate Presenter
        presenter = new MovieDetailsFragPressenterImpl(this);
    }
    //endregion

    //region On Create View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {
            mFragmenRoot = inflater.inflate(R.layout.layoit_movie_details_fragment, container, false);
        }

        catch (InflateException ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Inflate Exception", "onCreateOptionsMenu()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }

        return mFragmenRoot;
    }
    //endregion


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        //save the movie details which are currently being displayed, into the Bundle
        //so that they can be accessed in onActivityCreated when the orientation is changed

        try {
            outState.putParcelable(CommonGlobalObjects.getMovieDetailsKey(),
                    CommonGlobalObjects.getSelectedMovieDetails());
        }

        catch (Exception e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "onSaveInstanceState()", e.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {

            //get the movie id that was selected and passed to the detail activity
            String selectedMovieID = getActivity().getIntent().getStringExtra(CommonGlobalObjects.getSelectedMovieIdLiteral());

            //meaning, the fragment is being created due to change in the device orientation
            if (savedInstanceState != null && savedInstanceState.getParcelable(CommonGlobalObjects.getMovieDetailsKey()) != null) {

                //get the instance of MovieDetailsInfo from the bundle
                SelectedMovieData movieDetals =
                        savedInstanceState.getParcelable(CommonGlobalObjects.getMovieDetailsKey());

                //instance of Response handler class
                MovieResponseHandler responseHandler = new MovieResponseHandler();
                responseHandler.displayMovieDetails(getActivity(),
                        movieDetals);
                return;
            }

            //region Get Movie detail using netwrok call since fragment is being created for the first time
            //construct the URL by reading values from the Global Constants class and using the movie id
            String getSelectedMovieDetailsURL =
                    CommonGlobalObjects.getMovieDetailsURLPart1()
                            + selectedMovieID
                            + CommonGlobalObjects.getMovieDetailsURLPart2()
                            + CommonGlobalObjects.getMovieApiKey();

            //pass the url to the presenter and start the process of getting movie details
            presenter.showMovieDetails(getSelectedMovieDetailsURL);

        }catch (Exception ex){

            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "onActivityCreated()", ex.getMessage());
            Log.e(MOVIE_DETAILS_FRAG_LOG_TAG, errMsg);
            return ;
        }
    }

    //region Results are returned here from the presenter
    @Override
    public void deleiverMovieDetailsData(String jsonResult) {

        String selectedMovieData;
        selectedMovieData = jsonResult;

        //region No details found, notify user and exit
        if (selectedMovieData == null || selectedMovieData.isEmpty() ){
            Toast.makeText(getActivity(),CommonGlobalObjects.getMovieDetailsNotFoundMessage(),
                    Toast.LENGTH_LONG).show();
            return;
        }
        //endregion

        //parse the json and set the global object

        //SelectedMovieData d = new MovieJSONParser(selectedMovieData).getMovieDetails();
        CommonGlobalObjects.setSelectedMovieDetails(new MovieJSONParser(selectedMovieData).getMovieDetails());

        //Render the Movie Data (Selected Movie details)
        MovieResponseHandler responseHndlr = new MovieResponseHandler();
        responseHndlr.displayMovieDetails(getActivity(),CommonGlobalObjects.getSelectedMovieDetails());


    }
    //endregion

}
