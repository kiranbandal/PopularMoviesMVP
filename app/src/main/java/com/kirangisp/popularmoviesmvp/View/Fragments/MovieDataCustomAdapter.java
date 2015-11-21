package com.kirangisp.popularmoviesmvp.View.Fragments;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import com.kirangisp.commonandroidobjects.CommonGlobalObjects;
import com.kirangisp.commonandroidobjects.MoviePosterdata;
import com.kirangisp.commonandroidobjects.RunningDeviceProps;
import com.kirangisp.popularmoviesmvp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Custom adapter which is used to show the movie posters in the Movie Posters Fragment.
 */
public class MovieDataCustomAdapter extends BaseAdapter {

    private Context mContext; //for creating new image view
    private ArrayList<MoviePosterdata> mMovieDataList; //to hold the movies data passed in in the constructor

    private String mMoviePosterBaseURL; //to get the actual url of the movies poster
    private String mPosterImageSize;

    private final static String MOVIE_ADAPATER_LOG_TAG = "Movie Custom Adapter";

    private Drawable mPlaceHolderDrawable = null;
    private Drawable mErrorDrawable = null;

    public MovieDataCustomAdapter(Context c, ArrayList<MoviePosterdata> movieDataList) {

        //set member variables
        this.mContext = c;
        this.mMovieDataList = movieDataList;
        mPosterImageSize = RunningDeviceProps.getImgSizeToBeQueried();
        mMoviePosterBaseURL = CommonGlobalObjects.getMoviePosterBaseURL();
    }


    @Override
    public int getCount() {
        return mMovieDataList.size();
    }


    @Override
    public Object getItem(int position) {
        return mMovieDataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            ImageView moviePosterImgView;

          /*
            * the path retrieved in the json is relative to the movie poster, so we need to manipulate it,
            * we need to append a base path ahead of this relative path to build the complete url
            * so, get path value from the MovieInfo instance at the position that is passed in
            * and construct the full url to the poster
            * */
            String posterURL;
            posterURL = mMoviePosterBaseURL + mPosterImageSize + mMovieDataList.get(position).getMoviePosterPath();

            if (convertView == null) {

                // ImgaeView getting created for the first time
                moviePosterImgView = new ImageView(mContext);

                moviePosterImgView.setLayoutParams(new GridView.LayoutParams
                        (RunningDeviceProps.getMoviePosterImgViewWidth(),
                                RunningDeviceProps.getMoviePosterImgViewHeight()));

                //read padding value from global class and set it on the ImageView
                int imgViewPadding = RunningDeviceProps.getPosterImgViewPadding();
                moviePosterImgView.setPadding(imgViewPadding,
                        imgViewPadding,
                        imgViewPadding,
                        imgViewPadding);
            } else {
                //system sent the recycled image view
                moviePosterImgView = (ImageView) convertView;
            }

            //arratch the Movie Id as a Tag, so that it can be referred in detail activity
            moviePosterImgView.setTag(mMovieDataList.get(position).getMovieIDInAPI());

            //set the Place holder image from Drawable if not set already
            if (mPlaceHolderDrawable == null) {
                setPlaceHolderDrawable();
            }

            //set the Place holder image from Drawable if not set already
            if (mErrorDrawable == null) {
                setErrorDrawable();
            }

            //load image into the movie poster image view using Picasso
            Picasso.with(mContext).load(posterURL)
                    .placeholder(mPlaceHolderDrawable)
                    .error(mErrorDrawable)
                    .resize(RunningDeviceProps.getMoviePosterResizeWidth(),
                            RunningDeviceProps.getMoviePosterResizeHeight())
                    .into(moviePosterImgView);

            //moviePosterImgView.setImageResource(R.drawable.sample_0);
            return moviePosterImgView;

        } catch (NullPointerException e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Null Pointer Exception", "getView()", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
            return null;
        } catch (Exception e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "getView()", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
            return null;
        }
    }

    private void setPlaceHolderDrawable() {
        try {
            //get Place holder image from Drawable
            Bitmap placeHolderBMP = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.placeholder);
            Bitmap resizedPlaceHolderBMP = Bitmap.createScaledBitmap(placeHolderBMP, RunningDeviceProps.getMoviePosterResizeWidth(),
                    RunningDeviceProps.getMoviePosterResizeHeight(), true);
            mPlaceHolderDrawable = new BitmapDrawable(mContext.getResources(), resizedPlaceHolderBMP);

        } catch (Exception e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "setPlaceHolderDrawble", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
        }
    }

    private void setErrorDrawable() {
        try {
            //get Place holder image from Drawable
            Bitmap errorBMP = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.error);
            Bitmap resizedErrorBMP = Bitmap.createScaledBitmap(errorBMP, RunningDeviceProps.getMoviePosterResizeWidth(),
                    RunningDeviceProps.getMoviePosterResizeHeight(), true);
            mErrorDrawable = new BitmapDrawable(mContext.getResources(), resizedErrorBMP);

        } catch (Exception e) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception", "setErrorDrawable", e.getMessage());
            Log.e(MOVIE_ADAPATER_LOG_TAG, errMsg);
        }
    }
}
