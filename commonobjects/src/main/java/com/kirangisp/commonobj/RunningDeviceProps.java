package com.kirangisp.commonobj;

/**
 * Created by User on 18-Nov-15.
 */
public class RunningDeviceProps {

    //region Enum for holding the device orientation
    public enum DeviceOrientation {

        PORTRAIT,
        LANDSCAPE
    }
    //endregion

    //region Constructor
    //Empty Constructor
    public RunningDeviceProps() {

    }
    //endregion

    //region Device Orientation
    private static DeviceOrientation orientation;

    public static DeviceOrientation getOrientation() {
        return orientation;
    }

    public static void setOrientation(DeviceOrientation Oorientation) {
        orientation = Oorientation;
    }
    //endregion

    //region Device Density
    private static double deviceDensity;

    public static double getDeviceDensity() {
        return deviceDensity;
    }

    public static void setDeviceDensity(double density) {
        deviceDensity = density;
    }
    //endregion

    //region Screen Layout Size
    private static int screenLayoutSize;

    public static int getScreenLayoutSize() {
        return screenLayoutSize;
    }

    public static void setScreenLayoutSize(int layoutSize) {
        screenLayoutSize = layoutSize;
    }
    //endregion

    //region Image Size to be queried from movie db api
    public static String getImgSizeToBeQueried() {
        return imgSizeToBeQueried;
    }

    public static void setImgSizeToBeQueried(String size) {
        imgSizeToBeQueried = size;
    }

    private static String imgSizeToBeQueried;
    //endregion

    //region Movie Poster Img View (in Grid View) dimensions
    public static int getMoviePosterImgViewWidth() {
        return moviePosterImgViewWidth;
    }

    public static void setMoviePosterImgViewWidth(int wdth) {
        moviePosterImgViewWidth = wdth;
    }

    public static int getMoviePosterImgViewHeight() {
        return moviePosterImgViewHeight;
    }

    public static void setMoviePosterImgViewHeight(int ht) {
        moviePosterImgViewHeight = ht;
    }

    private static int moviePosterImgViewWidth;
    private static int moviePosterImgViewHeight;
    //endregion

    //region Movie Poster Image Resize dimension
    public static int getMoviePosterResizeWidth() {
        return moviePosterResizeWidth;
    }

    public static void setMoviePosterResizeWidth(int resizeWidth) {
        moviePosterResizeWidth = resizeWidth;
    }

    public static int getMoviePosterResizeHeight() {
        return moviePosterResizeHeight;
    }

    public static void setMoviePosterResizeHeight(int resizeHeight) {
        moviePosterResizeHeight = resizeHeight;
    }

    private static int moviePosterResizeWidth;
    private static int moviePosterResizeHeight;
    //endregion

    //region Movie Poster Image View Padding
    public static int getPosterImgViewPadding() {
        return posterImgViewPadding;
    }

    public static void setPosterImgViewPadding(int padding) {
        posterImgViewPadding = padding;
    }

    private static int posterImgViewPadding;
    //endregion

    //region Height and Width used in Movie Detail Fragment
    public static int getMovieDetailsPosterResizeWidth() {
        return movieDetailsPosterResizeWidth;
    }

    public static void setMovieDetailsPosterResizeWidth(int resizeWidth) {
        movieDetailsPosterResizeWidth = resizeWidth;
    }

    public static int getMovieDetailsPosterResizeHeight() {
        return movieDetailsPosterResizeHeight;
    }

    public static void setMovieDetailsPosterResizeHeight(int resizeHeight) {
        movieDetailsPosterResizeHeight = resizeHeight;
    }

    private static int movieDetailsPosterResizeWidth;
    private static int movieDetailsPosterResizeHeight;
    //endregion

}
