package com.kirangisp.popularmoviesmvp.Presenter.Activities;

import android.os.Bundle;

/**
 * Created by User on 18-Nov-15.
 */
public interface IPostersActivityView {

    boolean addPostersFragment(Bundle savedInstancee);

    void deleiverSortedMoviesJson( String sortedMoviesData);
}
