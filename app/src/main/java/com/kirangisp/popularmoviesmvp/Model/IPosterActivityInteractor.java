package com.kirangisp.popularmoviesmvp.Model;

import android.content.Context;

/**
 * Created by User on 18-Nov-15.
 */
public interface IPosterActivityInteractor {

    //To check if the device is connected to the internet
    boolean hasInternetConnection (Context appContext,String logTagName);
}
