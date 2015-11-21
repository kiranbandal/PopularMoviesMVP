package com.kirangisp.popularmoviesmvp.Model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
/**
 * Async Task that queries the movie db api.
 */
public class FetchMovieDataTask extends AsyncTask<Void, Void, String> {

    private INetworkCallFinished mQueryFinishedListener = null;
    private final static String FETCH_MOVIE_TASK_LOG_TAG = "Fetch Movie Task";
    private String mQueryURL;


    //parametrised constructor
    public FetchMovieDataTask(String queryURL, INetworkCallFinished listener) {
        mQueryFinishedListener = listener;
        mQueryURL = queryURL;
    }

    @Override
    protected String doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieDataResponseJsonStr = null;

        try {

            URL url = new URL(mQueryURL);

            // Create the request to MovieDB API, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            movieDataResponseJsonStr = buffer.toString();
        }
        catch (IOException ex) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("IO Exception", "doInBackground()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
            return null;

        } catch (Exception ex) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "doInBackground()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
            return null;
        }
        finally {

            //CLose the Net Connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            //close the reder
            if (reader != null) {
                try {
                    reader.close();
                } catch (IllegalArgumentException e) {
                    String errMsg = CommonGlobalObjects.constructErrorMsg("Illegal Argument Exception", "doInBackground()", e.getMessage());
                    Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
                } catch (final IOException e) {
                    String errMsg = CommonGlobalObjects.constructErrorMsg("IOE xception", "doInBackground()", e.getMessage());
                    Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
                }
            }

            return movieDataResponseJsonStr; //the response from the moviedb api is returned
        }
    }

    @Override
    protected void onPostExecute(String resultJSON) {
        super.onPostExecute(resultJSON);

        //if the result is not empty, then call the listener method
        if(resultJSON != null && !resultJSON.isEmpty()){

            mQueryURL = "";
            //call the method on the passed in listener and pass in the result
            mQueryFinishedListener.onSuccess(resultJSON);
        }
        else {

        }

    }
}
