package com.kirangisp.popularmoviesmvp.Model;

import android.content.Context;

/**
 * Created by User on 18-Nov-15.
 */
public class PosterFragmenInteractorImpl implements IPosterFragmentInteractor {

    @Override
    public void setDeviceImgeViewProps(Context cntxt) {
        PosterFragModelImplHelper.setProps(cntxt);
    }
}
