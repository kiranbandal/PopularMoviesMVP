package com.kirangisp.popularmoviesmvp.Presenter.Fragments;

import android.content.Context;


public interface IPosterFragmentPresenter {

    void setGlobalDeviceImgeViewProps(Context appContxt);

    void getPopularMoviesData(String apiURL);
}
