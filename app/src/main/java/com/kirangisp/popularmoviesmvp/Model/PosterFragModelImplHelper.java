package com.kirangisp.popularmoviesmvp.Model;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.kirangisp.commonobj.CommonGlobalObjects;
import com.kirangisp.commonobj.RunningDeviceProps;

/**
 * Helper class for Poster Fragment Interaction Impl
 */
public class PosterFragModelImplHelper {

    private static final String PSTRS_FRAG_HLPR_LOG_TAG = "Posters Frag Model Helper";

    protected static void setProps(Context appContext) {

        try {

            //get device density from Context and set it to the Global property
            double RunningDevicePropsDensity = appContext.getResources().getDisplayMetrics().density;
            RunningDeviceProps.setDeviceDensity(RunningDevicePropsDensity);

            //get screen size
            //get device screen size
            int screenSize = appContext.getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK;

            //set current device orientation
            if ((appContext.getResources().getConfiguration().orientation == 1)) {

                RunningDeviceProps.setOrientation(RunningDeviceProps.DeviceOrientation.PORTRAIT);
            } else if (appContext.getResources().getConfiguration().orientation == 2) {
                RunningDeviceProps.setOrientation(RunningDeviceProps.DeviceOrientation.LANDSCAPE);
            }

            //LDPI and MDPI
            if (RunningDevicePropsDensity == 1) {

                //Small screen (LDPI and MDPI)
                if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {

                    //Set resize dimensions for Movie Details Fragment
                    RunningDeviceProps.setMovieDetailsPosterResizeWidth(250);
                    RunningDeviceProps.setMovieDetailsPosterResizeHeight(325);

                    if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {
                        setLdpiMdpiSmallScreenPortraitProps();
                    } else {
                        setLdpiMdpiSmallScreenLandscapeProps();
                    }

                } else {
                    //medium and large screen (LDPI and MDPI)
                    if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {

                        //Portrait orientation

                        //Set resize dimensions for Movie Details Fragment
                        RunningDeviceProps.setMovieDetailsPosterResizeWidth(250);
                        RunningDeviceProps.setMovieDetailsPosterResizeHeight(325);

                        //image size to be queried from the movie db api
                        RunningDeviceProps.setImgSizeToBeQueried("w154/");

                        //set Image View Padding
                        RunningDeviceProps.setPosterImgViewPadding(1);

                        //set poster image view height and width
                        RunningDeviceProps.setMoviePosterImgViewHeight(265);
                        RunningDeviceProps.setMoviePosterImgViewWidth(305);

                        //set resize width/height of the image
                        RunningDeviceProps.setMoviePosterResizeHeight(285);
                        RunningDeviceProps.setMoviePosterResizeWidth(245);
                    } else {

                        //Set resize dimensions for Movie Details Fragment
                        RunningDeviceProps.setMovieDetailsPosterResizeWidth(250);
                        RunningDeviceProps.setMovieDetailsPosterResizeHeight(325);

                        //for landscape orientation
                        setHDPILandscapeProps();
                    }

                }
            }
            //if the device density is HDPI, e.g. Samsung Quattro, New Mpie 4C phone
            else if (RunningDevicePropsDensity == 1.5) {

                //Set resize dimensions for Movie Details Fragment
                RunningDeviceProps.setMovieDetailsPosterResizeWidth(250);
                RunningDeviceProps.setMovieDetailsPosterResizeHeight(325);

                if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {
                    setHDPIPortraitProps();
                } else {
                    setHDPILandscapeProps();
                }
            }

            //if the device density is xHDPI, Nexus 4
            else if (RunningDevicePropsDensity == 2) {

                //Set resize dimensions for Movie Details Fragment
                RunningDeviceProps.setMovieDetailsPosterResizeWidth(400);
                RunningDeviceProps.setMovieDetailsPosterResizeHeight(525);

                if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {
                    setXHDPIPortraitProps();
                } else {
                    //For Landscape orientation
                    setXHDPILandscapeProps();
                }
            }

            //if the device density is xxHDPI, Nexus 5
            else if (RunningDevicePropsDensity == 3) {

                //Set resize dimensions for Movie Details Fragment
                RunningDeviceProps.setMovieDetailsPosterResizeWidth(600);
                RunningDeviceProps.setMovieDetailsPosterResizeHeight(825);

                if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {
                    setXxHDPIPortraitProps();
                } else {
                    setXxHDPILandscapeProps();
                }
            }
            //if the device density is xxxHDPI, My S6
            else if (RunningDevicePropsDensity == 4) {

                //Set resize dimensions for Movie Details Fragment
                RunningDeviceProps.setMovieDetailsPosterResizeWidth(800);
                RunningDeviceProps.setMovieDetailsPosterResizeHeight(1000);

                if (RunningDeviceProps.getOrientation() == RunningDeviceProps.DeviceOrientation.PORTRAIT) {
                    setXxxHDPIPortraitProps();
                } else {
                    setXxxHDPILandscapeProps();
                }
            } else {
                //cater for for remaining screen densities
                //image size to be queried from the movie db api
                RunningDeviceProps.setImgSizeToBeQueried("w185/");

                //set Image View Padding
                RunningDeviceProps.setPosterImgViewPadding(1);

                //set poster image view height and width
                RunningDeviceProps.setMoviePosterImgViewHeight(265);
                RunningDeviceProps.setMoviePosterImgViewWidth(305);

                //set resize width/height of the image
                RunningDeviceProps.setMoviePosterResizeHeight(285);
                RunningDeviceProps.setMoviePosterResizeWidth(245);
            }

        } catch (NullPointerException nullEx) {

            String errMsg = CommonGlobalObjects.constructErrorMsg("Null Pointer Exception ",
                    "setGlobalDeviceImgeViewProps()",
                    nullEx.getMessage());
            Log.e(PSTRS_FRAG_HLPR_LOG_TAG, errMsg);

            //image size to be queried from the movie db api
            RunningDeviceProps.setImgSizeToBeQueried("w185/");

            //set Image View Padding
            RunningDeviceProps.setPosterImgViewPadding(1);

            //set poster image view height and width
            RunningDeviceProps.setMoviePosterImgViewHeight(265);
            RunningDeviceProps.setMoviePosterImgViewWidth(305);

            //set resize width/height of the image
            RunningDeviceProps.setMoviePosterResizeHeight(285);
            RunningDeviceProps.setMoviePosterResizeWidth(245);

        } catch (Exception ex) {
            String errMsg = CommonGlobalObjects.constructErrorMsg("Generic Exception ",
                    "setGlobalDeviceImgeViewProps()",
                    ex.getMessage());
            Log.e(PSTRS_FRAG_HLPR_LOG_TAG, errMsg);

            //image size to be queried from the movie db api
            RunningDeviceProps.setImgSizeToBeQueried("w185/");

            //set Image View Padding
            RunningDeviceProps.setPosterImgViewPadding(1);

            //set poster image view height and width
            RunningDeviceProps.setMoviePosterImgViewHeight(265);
            RunningDeviceProps.setMoviePosterImgViewWidth(305);

            //set resize width/height of the image
            RunningDeviceProps.setMoviePosterResizeHeight(285);
            RunningDeviceProps.setMoviePosterResizeWidth(245);
        }
    }

    //region For xxx HPI
    protected static void setXxxHDPIPortraitProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w780/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(5);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(830);
        RunningDeviceProps.setMoviePosterImgViewWidth(1000);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(980);
        RunningDeviceProps.setMoviePosterResizeWidth(820);
    }

    protected static void setXxxHDPILandscapeProps() {
        //
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("original");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(1275);
        RunningDeviceProps.setMoviePosterImgViewWidth(1090);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(1260);
        RunningDeviceProps.setMoviePosterResizeWidth(1080);

    }
    //endregion

    //region For XX HDPI
    protected static void setXxHDPIPortraitProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w500/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(4);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(630);
        RunningDeviceProps.setMoviePosterImgViewWidth(800);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(755);
        RunningDeviceProps.setMoviePosterResizeWidth(615);

    }

    protected static void setXxHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w780/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(990);
        RunningDeviceProps.setMoviePosterImgViewWidth(815);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(990);
        RunningDeviceProps.setMoviePosterResizeWidth(815);

    }
    //endregion

    //region For xHDPI
    protected static void setXHDPIPortraitProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w342/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(405);
        RunningDeviceProps.setMoviePosterImgViewWidth(495);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(438);
        RunningDeviceProps.setMoviePosterResizeWidth(395);
    }

    protected static void setXHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w500/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(2);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(700);
        RunningDeviceProps.setMoviePosterImgViewWidth(550);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(680);
        RunningDeviceProps.setMoviePosterResizeWidth(550);
    }
    //endregion

    //region For HDPI
    protected static void setHDPIPortraitProps() {
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w185/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(1);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(265);
        RunningDeviceProps.setMoviePosterImgViewWidth(305);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(285);
        RunningDeviceProps.setMoviePosterResizeWidth(245);
    }

    protected static void setHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w342/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(450);
        RunningDeviceProps.setMoviePosterImgViewWidth(375);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(430);
        RunningDeviceProps.setMoviePosterResizeWidth(360);
    }
    //endregion

    //region LDPI and MDPI - Small screen
    protected static void setLdpiMdpiSmallScreenPortraitProps() {
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w92/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(1);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(135);
        RunningDeviceProps.setMoviePosterImgViewWidth(155);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(150);
        RunningDeviceProps.setMoviePosterResizeWidth(130);
    }

    protected static void setLdpiMdpiSmallScreenLandscapeProps() {
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w92/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(1);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(135);
        RunningDeviceProps.setMoviePosterImgViewWidth(155);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(150);
        RunningDeviceProps.setMoviePosterResizeWidth(130);
    }
    //endregion

    //region For LDPI and MDPI - Large screen
    protected static void setLdpiMdpiLargeScreenPortraitProps() {
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w92/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(1);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(135);
        RunningDeviceProps.setMoviePosterImgViewWidth(155);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(150);
        RunningDeviceProps.setMoviePosterResizeWidth(130);
    }

    protected static void setLdpiMdpiLargeScreenLandscapeProps() {
        //image size to be queried from the movie db api
        RunningDeviceProps.setImgSizeToBeQueried("w92/");

        //set Image View Padding
        RunningDeviceProps.setPosterImgViewPadding(1);

        //set poster image view height and width
        RunningDeviceProps.setMoviePosterImgViewHeight(135);
        RunningDeviceProps.setMoviePosterImgViewWidth(155);

        //set resize width/height of the image
        RunningDeviceProps.setMoviePosterResizeHeight(150);
        RunningDeviceProps.setMoviePosterResizeWidth(130);
    }
    //endregion


}
