package com.kirangisp.popularmoviesmvp.Presenter;

import android.content.Context;

import com.kirangisp.commonandroidobjects.MoviePosterData;

import java.util.ArrayList;


public interface IPosterFragmentPresenter {

    void setGlobalDeviceImgeViewProps(Context appContxt);

    void getPopularMoviesData(String apiURL);
}
