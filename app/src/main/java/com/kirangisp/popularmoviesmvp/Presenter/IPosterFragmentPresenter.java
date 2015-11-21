package com.kirangisp.popularmoviesmvp.Presenter;

import android.content.Context;


public interface IPosterFragmentPresenter {

    void setGlobalDeviceImgeViewProps(Context appContxt);

    void getPopularMoviesData(String apiURL);
}
