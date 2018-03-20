package com.cerquitica.retrofiteasyrequest.request;

import android.content.Context;

import com.cerquitica.retrofiteasyrequest.interfaces.ILoadingFragment;
import com.cerquitica.retrofiteasyrequest.interfaces.TaskExecutor;
import com.cerquitica.retrofiteasyrequest.model.dao.CategoryModel;
import com.cerquitica.retrofiteasyrequest.model.services.api.CategoryFullCall;
import com.cerquitica.retrofiteasyrequest.model.services.serializable.ParseModelCategory;
import com.cerquitica.retrofiteasyrequest.request.baseClass.Config;
import com.cerquitica.retrofiteasyrequest.request.baseClass.GetAllDataGeneric;
import com.cerquitica.retrofiteasyrequest.request.baseClass.Utils;

import com.octo.android.robospice.SpiceManager;

/**
 * Created by tico on 18/07/2016.
 */
public class CategoryUpdate extends GetAllDataGeneric {

    private Context context;
    private int countTaskCompleted = 0;
    private boolean completed = false;
    public static int cant_ent = 2;
    private static String CONS_CLASS = "CATEGORY";

    public CategoryUpdate(ILoadingFragment starPointInterface) {
        super(CONS_CLASS,starPointInterface.getContext(),cant_ent);
        this.context=starPointInterface.getContext();
    }

    public CategoryUpdate(SpiceManager spiceManager, Context context) {
        super(spiceManager,CONS_CLASS,context,cant_ent);
        this.context=context;
    }



    public void updateCategory(int counter, final TaskExecutor executor) {
        if (Utils.getInstance().isOnline(context)) {
            Class p = ParseModelCategory.Category.class;
            Class c = CategoryFullCall.Category.class;
            Class d = CategoryModel.Category.class;
            updateWebServiceSimple(null,null,p, c, d, executor, counter, Config.AMOUNT_ELEMENTS_REQUEST, 1);
        }
    }

    public void updateSubCategory(final int counter, final TaskExecutor executor) {
        if (Utils.getInstance().isOnline(context)) {
            Class p = ParseModelCategory.SubCategory.class;
            Class c = CategoryFullCall.SubCategory.class;
            Class d = CategoryModel.SubCategory.class;
            updateWebServiceSimple(null,null,p, c, d, executor, counter, Config.AMOUNT_ELEMENTS_REQUEST, 1);
        }
    }






}
