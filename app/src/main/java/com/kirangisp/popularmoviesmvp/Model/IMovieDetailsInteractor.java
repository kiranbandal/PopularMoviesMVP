package com.kirangisp.popularmoviesmvp.Model;

/**
 * Model interface for Movie Details
 */
public interface IMovieDetailsInteractor {

    void getSelectedMovieInfo(String selectedMovieUrl, final INetworkCallFinished queryListener);
}
