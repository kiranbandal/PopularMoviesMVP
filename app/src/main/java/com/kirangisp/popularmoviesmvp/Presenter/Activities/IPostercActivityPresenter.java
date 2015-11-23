package com.kirangisp.popularmoviesmvp.Presenter.Activities;

import android.content.Context;

/**
 * Created by User on 18-Nov-15.
 */
public interface IPostercActivityPresenter {

    boolean isDeviceConnectedToInternet(Context appContext,String logTagName);

    void getMoviesJson(String apiURL);
}
