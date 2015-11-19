package com.kirangisp.popularmoviesmvp.Presenter;

/**
 * Created by User on 18-Nov-15.
 */
public interface IPosterFragmentView {

    /**
     * Return the popular movie data json to the fragment. It will be the fragment's
     * responsibility to format that raw json and use it to render the poster gridview
     */

    void deliverPopularMoviesDataInJson(String popularMoviesJSON);
}
