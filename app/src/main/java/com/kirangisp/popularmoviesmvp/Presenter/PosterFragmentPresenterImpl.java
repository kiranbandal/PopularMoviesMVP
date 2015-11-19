package com.kirangisp.popularmoviesmvp.Presenter;

import android.content.Context;

import com.kirangisp.popularmoviesmvp.Model.IPosterFragmentInteractor;
import com.kirangisp.popularmoviesmvp.Model.PosterFragmenInteractorImpl;

/**
 * Created by User on 18-Nov-15.
 */
public class PosterFragmentPresenterImpl implements IPosterFragmentPresenter{

    //Reference to the Fragment View
    IPosterFragmentView view;

    //reference to fragment model
    IPosterFragmentInteractor fragmntModel;

    public PosterFragmentPresenterImpl(IPosterFragmentView fragmntView){

        //set View
        view = fragmntView;

        //set Model
        fragmntModel = new PosterFragmenInteractorImpl();
    }

    @Override
    public void setGlobalDeviceImgeViewProps(Context appContxt) {

        //set global properties related to device, also PosterImageView size and resize dimensions
        fragmntModel.setDeviceImgeViewProps(appContxt);
    }
}
