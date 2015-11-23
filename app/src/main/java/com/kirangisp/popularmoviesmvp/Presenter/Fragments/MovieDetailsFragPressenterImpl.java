package com.kirangisp.popularmoviesmvp.Presenter.Fragments;

import com.kirangisp.popularmoviesmvp.Model.IMovieDetailsInteractor;
import com.kirangisp.popularmoviesmvp.Model.INetworkCallFinished;
import com.kirangisp.popularmoviesmvp.Model.MovieDetailsInteractorImpl;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IMovieDetailsFragPressenter;
import com.kirangisp.popularmoviesmvp.Presenter.Fragments.IMovieDetaisFragmentView;

/**
 * Created by User on 21-Nov-15.
 */
public class MovieDetailsFragPressenterImpl implements IMovieDetailsFragPressenter,INetworkCallFinished {

    //reference to the model
    IMovieDetailsInteractor model;

    //reference to the View
    IMovieDetaisFragmentView view;

    //region Constructor
    public MovieDetailsFragPressenterImpl(IMovieDetaisFragmentView detailsFragView){

        this.view = detailsFragView;
        this.model = new MovieDetailsInteractorImpl();
    }
    //endregion

    @Override
    public void showMovieDetails(String selectedMovieURL) {
        //pass in the URL with the instance
        model.getSelectedMovieInfo(selectedMovieURL,this);
    }

    //region INetworkCallFinished implementation
    @Override
    public void onSuccess(String queryResult) {
        view.deleiverMovieDetailsData(queryResult);
    }

    @Override
    public void onError(String errorMsg) {

    }
    //endregion
}
