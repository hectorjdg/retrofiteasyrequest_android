package com.cerquitica.retrofiteasyrequest.model.services.api;

import com.cerquitica.retrofiteasyrequest.model.services.serializable.ParseModelCategory;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by tico on 18/07/2016.
 */
public class CategoryFullCall {
    public  interface Category {
        @GET("/api/categories/servercounter_lastsync/{counter}")
        void  getData(@Header("Authorization") String token, @Path("counter") int counter, @Query("fields") String fields, @Query("page_size") int page_size, @Query("page") int page, Callback<ParseModelCategory.Category> routesCallback);
    }


    public  interface SubCategory {
        @GET("/api/sub-categories/servercounter_lastsync/{counter}")
        void  getData(@Header("Authorization") String token, @Path("counter") int counter, @Query("fields") String fields, @Query("page_size") int page_size, @Query("page") int page, Callback<ParseModelCategory.SubCategory> routesCallback);
    }


}
