package com.kirangisp.popularmoviesmvp.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;

/**
 * Created by User on 18-Nov-15.
 */
public class PosterActivityInteractionImpl implements IPosterActivityInteractor {

    @Override
    public boolean hasInternetConnection(Context appContext, String logTagName) {

        // Whether there is a Wi-Fi connection.
        boolean wifiConnected = false;
        // Whether there is a mobile connection.
        boolean mobileConnected = false;

        try {

            ConnectivityManager connMgr =
                    (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();

            if (activeInfo != null && activeInfo.isConnected()) {

                wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;

                if (wifiConnected || mobileConnected) {
                    return true;
                }
            }

            else {
                return false;
            }
        } catch (SecurityException securitEx) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Security Exception", "isPhoneConnectedToInternet()", securitEx.getMessage());
            Log.e(logTagName, errMsg);
            return false;

        } catch (NullPointerException ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Null Reference Exception", "isPhoneConnectedToInternet()", ex.getMessage());
            Log.e(logTagName, errMsg);
            return false;

        } catch (Exception e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "isPhoneConnectedToInternet()", e.getMessage());
            Log.e(logTagName, errMsg);
            return false;
        }

        return false;

    }

    @Override
    public void getSortedMovies(String apiQueryURL, INetworkCallFinished finishedListener) {
        new FetchMovieDataTask(apiQueryURL,finishedListener).execute();
    }
}
