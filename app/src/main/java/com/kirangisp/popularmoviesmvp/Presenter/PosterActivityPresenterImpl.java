package com.kirangisp.popularmoviesmvp.Presenter;

import android.content.Context;

import com.kirangisp.popularmoviesmvp.Model.INetworkCallFinished;
import com.kirangisp.popularmoviesmvp.Model.IPosterActivityInteractor;
import com.kirangisp.popularmoviesmvp.Model.PosterActivityInteractionImpl;

/**
 * Created by User on 18-Nov-15.
 */
public class PosterActivityPresenterImpl implements IPostercActivityPresenter, INetworkCallFinished{

    //reference to the View
    IPostersActivityView posterActivityView;

    //reference to the Interactor (i.e. Model)
    IPosterActivityInteractor posterModel;

    //region Constructor
    public PosterActivityPresenterImpl(IPostersActivityView view){

        //set reference to the View
        this.posterActivityView = view;

        //set reference to the Model
        this.posterModel = new PosterActivityInteractionImpl();
    }

    @Override
    public boolean isDeviceConnectedToInternet(Context appContext, String logTagName) {

        //call the method on the module to check if the device is connected to Internet
        return posterModel.hasInternetConnection(appContext,logTagName);
    }

    @Override
    public void getMoviesJson(String apiURL) {
        //call the Model method here, to start the Sort movie process
        posterModel.getSortedMovies(apiURL,this);
    }


    //endregion

    //region INetworkCallFinished implementation
    @Override
    public void onSuccess(String queryResult) {
        posterActivityView.deleiverSortedMoviesJson(queryResult);
    }

    @Override
    public void onError(String errorMsg) {

    }
    //endregion

}
