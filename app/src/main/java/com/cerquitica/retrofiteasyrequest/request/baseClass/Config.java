package com.cerquitica.retrofiteasyrequest.request.baseClass;

import com.cerquitica.retrofiteasyrequest.model.dao.CategoryModel;

public class Config {
    public final static String BASE_URL = "http://192.168.115.102:8000";
    public final static int AMOUNT_ELEMENTS_REQUEST = 10;

    public static Class[] classActivityModelArray = {
            CategoryModel.class,
    };

}