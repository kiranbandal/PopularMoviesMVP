package com.kirangisp.popularmoviesmvp.Model;

/**
 * This interface is created to monitor the network calls like AsyncTask. The presenter has to implement
 * this interface, also the class in the Model takes instance of this as parameter. Then it calls the methods on this
 * interface as the Network call executes, if there was sucess, then, onSuccess if there was an error, onError is called.
 */
public interface INetworkCallFinished {


    void onSuccess(String queryResult);

    void onError(String errorMsg);

}
