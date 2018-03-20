package com.cerquitica.retrofiteasyrequest.request.baseClass;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.reflect.Field;

public class Utils {

    private static Utils instance;

    public static void initInstance() {
        if (instance == null) {
            instance = new Utils();
        }
    }

    public static Utils getInstance() {
        // Return the instance
        initInstance();
        return instance;
    }


    public static String getAllFieldsFromClass(Class<?> classTemp) {
        String fields = "";
        Field[] fieldsArray = classTemp.getDeclaredFields();
        for (Field fieldItem : fieldsArray) {
            fields += fieldItem.getName() + ",";
        }
        return fields;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}