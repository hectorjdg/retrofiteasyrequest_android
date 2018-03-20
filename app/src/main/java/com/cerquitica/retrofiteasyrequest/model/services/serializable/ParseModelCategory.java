package com.cerquitica.retrofiteasyrequest.model.services.serializable;

import com.cerquitica.retrofiteasyrequest.model.dao.CategoryModel;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by tico on 18/07/2016.
 */
public class ParseModelCategory {
    public static class Category {
        @SerializedName("count")
        public int number;
        @SerializedName("results")
        public List<CategoryModel.Category> list;

        public void setList(List<CategoryModel.Category> list) {
            this.list = list;
        }
    }


    public static class SubCategory {
        @SerializedName("count")
        public int number;
        @SerializedName("results")
        public List<CategoryModel.SubCategory> list;

        public void setList(List<CategoryModel.SubCategory> list) {
            this.list = list;
        }
    }
}
