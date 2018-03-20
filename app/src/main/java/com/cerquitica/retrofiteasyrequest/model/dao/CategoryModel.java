package com.cerquitica.retrofiteasyrequest.model.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by carlos on 11/02/2016.
 */
public class CategoryModel {
    @DatabaseTable
    public static class Category implements Parcelable {

        @Expose
        @DatabaseField(columnName="id",id=true)
        private UUID id;

        @Expose
        @DatabaseField(columnName = "category", dataType = DataType.STRING)
        private String category;

        @Expose
        @DatabaseField(columnName = "iconData", dataType = DataType.STRING_BYTES)
        private String iconData;

        @Expose
        @DatabaseField(columnName = "color")
        private String color;

        @Expose
        @DatabaseField(columnName = "isCoomingSoon", dataType = DataType.BOOLEAN)
        private boolean isCoomingSoon;


        @Expose
        @DatabaseField(columnName = "timestamp_last_update")
        private Date timestamp_last_update;
        
        @Expose
        @DatabaseField(columnName = "counter_last_update")
        private int counter_last_update;
        @Expose
        @DatabaseField(columnName = "deleted_at")
        private String deleted_at;

        public String getDeleted_at(){
            return deleted_at;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeSerializable(id);
            parcel.writeString(category);
            parcel.writeString(iconData);
            parcel.writeString(color);
            parcel.writeByte((byte) (isCoomingSoon ? 1 : 0));
        }

        private void readFromParcel(Parcel in) {
            id= (UUID) in.readSerializable();
            category = in.readString();
            iconData = in.readString();
            color=in.readString();
            isCoomingSoon=in.readByte() != 0;
        }

        public Category() {
        }


        public Category(String category, String iconData, String color, boolean isCoomingSoon, Date timestamp_last_update, int counter_last_update, String deleted_at) {
            this.category = category;
            this.iconData = iconData;
            this.color = color;
            this.isCoomingSoon = isCoomingSoon;
            this.timestamp_last_update = timestamp_last_update;
            this.counter_last_update = counter_last_update;
            this.deleted_at = deleted_at;
        }

        public Category(Parcel in) {
            readFromParcel(in);
        }

        public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
            public Category createFromParcel(Parcel in) {
                return new Category(in);
            }
            public Category[] newArray(int size) {
                return new Category[size];
            }
        };

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getIconData() {
            return iconData;
        }

        public void setIconData(String iconData) {
            this.iconData = iconData;
        }

        public boolean isCoomingSoon() {
            return isCoomingSoon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setCoomingSoon(boolean coomingSoon) {
            isCoomingSoon = coomingSoon;
        }

        public void setDeleted_at(String deleted_at) {
            this.deleted_at = deleted_at;
        }

        public int getCounter_last_update() {
            return counter_last_update;
        }

        public void setCounter_last_update(int counter_last_update) {
            this.counter_last_update = counter_last_update;
        }

        public Date getTimestamp_last_update() {
            return timestamp_last_update;
        }

        public void setTimestamp_last_update(Date timestamp_last_update) {
            this.timestamp_last_update = timestamp_last_update;
        }
    }

    @DatabaseTable
    public static class SubCategory implements Parcelable {

        @Expose
        @DatabaseField(/*id = true,*/ columnName = "id",id = true)
        private UUID id;

//
//  @SerializedName(value = "category")
        @DatabaseField(columnName = "category", foreign = true)
        private Category category;

        @Expose
        @DatabaseField(columnName = "sub_category", dataType = DataType.STRING)
        private String subCategory;

        @Expose
        @DatabaseField(columnName = "description", dataType = DataType.STRING)
        private String decription;

        @Expose
        @DatabaseField(columnName = "icon_data", dataType = DataType.STRING_BYTES)
        private String iconData;


        @Expose
        @DatabaseField(columnName = "is_food", dataType = DataType.BOOLEAN)
        private boolean is_food;

        @Expose
        @DatabaseField(columnName = "imageData", dataType = DataType.STRING_BYTES)
        private String imageData;

        private int amountItems=0;

        private int amountNew;


        @Expose
        @DatabaseField(columnName = "timestamp_last_update")
        private Date timestamp_last_update;
        @Expose
        @DatabaseField(columnName = "counter_last_update")
        private int counter_last_update;          @Expose         @DatabaseField(columnName = "deleted_at")         private String deleted_at;                  public String getDeleted_at(){             return deleted_at;         }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeSerializable(id);
            parcel.writeParcelable(category, flags);
            parcel.writeString(subCategory);
            parcel.writeString(decription);
            parcel.writeString(iconData);
            parcel.writeString(imageData);
            parcel.writeByte((byte) (is_food ? 1 : 0));
        }

        private void readFromParcel(Parcel in) {
            id= (UUID) in.readSerializable();
            category = in.readParcelable(Category.class.getClassLoader());
            subCategory = in.readString();
            decription=in.readString();
            iconData = in.readString();
            imageData =in.readString();
            is_food=in.readByte() != 0;

        }

        public SubCategory() {
        }

        public SubCategory(Category category, String subCategory, String decription, String iconData, boolean is_food, String image_data, Date timestamp_last_update, int counter_last_update, String deleted_at) {
            this.category = category;
            this.subCategory = subCategory;
            this.decription = decription;
            this.iconData = iconData;
            this.is_food = is_food;
            this.imageData = image_data;
            this.timestamp_last_update = timestamp_last_update;
            this.counter_last_update = counter_last_update;
            this.deleted_at = deleted_at;
        }

        public SubCategory(Category category, String subCategory) {
            this.category = category;
            this.subCategory = subCategory;
        }

        public SubCategory(Parcel in) {
            readFromParcel(in);
        }

        public static final Creator CREATOR = new Creator() {
            public SubCategory createFromParcel(Parcel in) {
                return new SubCategory(in);
            }

            public SubCategory[] newArray(int size) {
                return new SubCategory[size];
            }
        };

        public UUID getId() {
            return id;
        }

        public Category getCategory() {
            return category;
        }

        public String getSubCategory() {
            return subCategory;
        }

        public String getDecription() {
            return decription;
        }

        public String getIconData() {
            return iconData;
        }

        public boolean is_food() {
            return is_food;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public void setDecription(String decription) {
            this.decription = decription;
        }

        public void setIconData(String iconData) {
            this.iconData = iconData;
        }

        public void setIs_food(boolean is_food) {
            this.is_food = is_food;
        }

        public String getImageData() {
            return imageData;
        }

        public void setImageData(String imageData) {
            this.imageData = imageData;
        }

        public Date getTimestamp_last_update() {
            return timestamp_last_update;
        }

        public void setTimestamp_last_update(Date timestamp_last_update) {
            this.timestamp_last_update = timestamp_last_update;
        }

        public int getCounter_last_update() {
            return counter_last_update;
        }

        public void setCounter_last_update(int counter_last_update) {
            this.counter_last_update = counter_last_update;
        }

        public void setDeleted_at(String deleted_at) {
            this.deleted_at = deleted_at;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public int getAmountItems() {
            return amountItems;
        }

        public void setAmountItems(int amountItems) {
            this.amountItems = amountItems;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public int getAmountNew() {
            return amountNew;
        }

        public void setAmountNew(int amountNew) {
            this.amountNew = amountNew;
        }
    }
}
