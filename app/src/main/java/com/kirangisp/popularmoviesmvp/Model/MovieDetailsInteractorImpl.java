package com.kirangisp.popularmoviesmvp.Model;

import android.os.Bundle;

/**
 * Created by User on 22-Nov-15.
 */
public class MovieDetailsInteractorImpl implements IMovieDetailsInteractor {

    @Override
    public void getSelectedMovieInfo(String selectedMovieUrl, INetworkCallFinished queryListener) {
        new FetchMovieDataTask(selectedMovieUrl,queryListener).execute();
    }


}
