package com.kirangisp.popularmoviesmvp.Presenter.Fragments;

import android.content.Context;

import com.kirangisp.popularmoviesmvp.Model.IPosterFragmentInteractor;
import com.kirangisp.popularmoviesmvp.Model.PosterFragmenInteractorImpl;
import com.kirangisp.popularmoviesmvp.Model.INetworkCallFinished;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IPosterFragmentPresenter;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IPosterFragmentView;


/**
 * Popular Movies Fragment Presenter Implementation class.
 */
public class PosterFragmentPresenterImpl implements IPosterFragmentPresenter, INetworkCallFinished {

    //Reference to the Fragment View
    IPosterFragmentView view;

    //reference to fragment model
    IPosterFragmentInteractor popularMoviesFragmentModel;

    public PosterFragmentPresenterImpl(IPosterFragmentView fragmntView) {

        //set View
        view = fragmntView;

        //set Model
        popularMoviesFragmentModel = new PosterFragmenInteractorImpl();
    }

    //region IPosterFragmentPresenter implementation
    @Override
    public void setGlobalDeviceImgeViewProps(Context appContxt) {

        //set global properties related to device, also PosterImageView size and resize dimensions
        popularMoviesFragmentModel.setDeviceImgeViewProps(appContxt);
    }

    @Override
    public void getPopularMoviesData(String url) {
        //call the method on the model to start the process of getting popular movies json data
        popularMoviesFragmentModel.getMoviePostersData(url, this);
    }

    //endregion

    //region INetworkCallFinished implementation
    @Override
    public void onSuccess(String queryResult) {
        try {
            //return the json result to the View.
            view.deliverPopularMoviesDataInJson(queryResult);
        }
        catch (Exception e) {
            String err;
            err = e.getMessage();
        }

    }

    @Override
    public void onError(String errorMsg) {

    }
    //endregion
}
