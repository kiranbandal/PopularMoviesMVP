package com.kirangisp.popularmoviesmvp.Model;

import android.os.Bundle;

/**
 * Model interface for Movie Details
 */
public interface IMovieDetailsInteractor {

    void getSelectedMovieInfo(String selectedMovieUrl, final INetworkCallFinished queryListener);


}
