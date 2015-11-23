package com.kirangisp.popularmoviesmvp.Presenter.Activities;

import android.os.Bundle;

import com.kirangisp.popularmoviesmvp.Presenter.Activities.IMovieDetailsActivityPresenter;
import com.kirangisp.popularmoviesmvp.Presenter.Activities.IMovieDetailsActivityView;

/**
 * Created by User on 22-Nov-15.
 */
public class MovieDetailsActivityPresenterImpl implements IMovieDetailsActivityPresenter {

    //reference to the Model here

    //reference to the View
    IMovieDetailsActivityView view;

    public MovieDetailsActivityPresenterImpl(IMovieDetailsActivityView movideDetailsActiView){

        this.view = movideDetailsActiView;
    }



}
