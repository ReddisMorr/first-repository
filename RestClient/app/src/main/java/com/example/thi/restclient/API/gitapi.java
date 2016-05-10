package com.example.thi.restclient.API;

import com.example.thi.restclient.model.gitmodel;

/**
 * Created by THI on 14.04.2016.
 */
public interface gitapi {
    @GET("/users/{user}")      //here is the other url part.best way is to start using /
    public void getFeed(@Path("user") String user,Callback<gitmodel> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
