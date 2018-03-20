package com.cerquitica.retrofiteasyrequest.interfaces;

import android.content.Context;



import com.octo.android.robospice.SpiceManager;

import java.util.List;

/**
 * Created by Alquimista on 3/2/2016.
 */
public interface ILoadingFragment{
    void finishedTask(Object object);
    Context getContext();
    SpiceManager getSpiceManager();

}
