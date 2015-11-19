package com.kirangisp.commonobj;

/*
* Holds the request details. Instance of this class is passed to the FetchMovieData task.
* */
public class FetchMovieRequest {

    private String queryURL;
    private FetchMovieRequestType requestType;
    private String logTagName;


    public FetchMovieRequest(String url, FetchMovieRequestType rqstType) {
        this.queryURL = url;
        this.requestType = rqstType;
    }

    public FetchMovieRequest(String url, FetchMovieRequestType rqstType, String logTag) {
        this.queryURL = url;
        this.requestType = rqstType;
        this.logTagName = logTag;
    }

    public String getQueryURL() {
        return queryURL;
    }

    public String getLogTagName() {
        return logTagName;
    }

    public FetchMovieRequestType getRequestType() {
        return requestType;
    }
}
