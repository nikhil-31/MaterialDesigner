package com.example.nikhil.materialtester.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.nikhil.materialtester.MyApplication;

/**
 * Created by nikhil on 12-08-2016.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance =null;
    private RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
