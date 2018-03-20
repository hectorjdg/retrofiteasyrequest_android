package com.cerquitica.retrofiteasyrequest.request.baseClass;

public class ObjUpdate {
    private static ObjUpdate ourInstance = new ObjUpdate();
    private String data;
    private int number;
    private boolean completed=false;

    public static ObjUpdate getInstance(String data,int number,boolean completed) {
        ourInstance.setData(data);
        ourInstance.setNumber(number);
        ourInstance.setCompleted(completed);
        return ourInstance;
    }

    private ObjUpdate() {
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}