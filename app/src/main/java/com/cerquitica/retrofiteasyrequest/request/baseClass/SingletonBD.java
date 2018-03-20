package com.cerquitica.retrofiteasyrequest.request.baseClass;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

public class SingletonBD<T> {
    private static final String DATABASE_NAME ="DATABASE_NAME" ;
    private static SingletonBD instance;

    private static DBHelper dbHelper;


    public static String customVar = "Hello";
    private static Context context;


    public static void initInstance(Context context) {
        if (instance == null) {
            SingletonBD.context = context;
            initHelper();
            instance = new SingletonBD();
        }
    }

    public static SingletonBD getInstance(Context context) {
        // Return the instance
        initInstance(context);
        return instance;
    }

    public Dao<T, Integer> getModelActivity(Class<T> modelClassName)
            throws Exception {
        getDbHelper().getWritableDatabase();
        Dao<T, Integer> objectDao= getDbHelper().getDao(modelClassName);
        return objectDao;
    }

    private SingletonBD() {
        // Constructor hidden because this is a singleton
    }


    private static DBHelper initHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context,DATABASE_NAME);
        }
        return dbHelper;
    }

    public DBHelper getDbHelper(){
        return dbHelper;
    }



}