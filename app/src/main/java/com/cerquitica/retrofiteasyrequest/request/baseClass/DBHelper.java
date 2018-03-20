package com.cerquitica.retrofiteasyrequest.request.baseClass;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DBHelper<T> extends OrmLiteSqliteOpenHelper {

    private String DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_PASSWORD = "tourinfo";
    private ConnectionSource connectionSource;
    private Class classTempFull;


    public DBHelper(Context context,String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public Dao<T, Integer> getModelActivity(Class<T> modelClassName)
            throws Exception {
           return getDao(modelClassName);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Class[] classActivityModelArray= Config.classActivityModelArray;
            for(Class classActivityModel : classActivityModelArray){
                Class[] clasesActivity=classActivityModel.getClasses();
                for (Class classTemp : clasesActivity) {
                    classTempFull=classTemp;
                    TableUtils.createTableIfNotExists(connectionSource, classTempFull);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.d("ERROR DB", classTempFull.getName());

            //EventBus.getDefault().post("Error :"+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    /*
                             * (non-Javadoc)
                             *
                             * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#close()
                             */
    @Override
    public void close() {
        // TODO Auto-generated method stub
        super.close();
    }

}
