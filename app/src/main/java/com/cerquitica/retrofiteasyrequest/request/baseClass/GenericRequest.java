package com.cerquitica.retrofiteasyrequest.request.baseClass;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Alquimista on 11/20/2015.
 */
public class GenericRequest<T,S> extends RetrofitSpiceRequest<T,S> {

    private String auth;
    T objReturn;
    private int counter;
    private String fields;
    private int page=1;
    private boolean finish_req=false;
    private String data="";
    private Class<T> tClass;
    private Class<S> sClass;
    private String idObject="";

    Object k;
    Class<S> service;
    public GenericRequest(String auth, int counter,String fields,Class<T> tClass,Class<S> sClass,int page) {
        super(tClass,sClass);
        this.tClass=tClass;
        this.sClass=sClass;
        this.auth=auth;
        this.counter=counter;
        this.fields=fields;
        this.page=page;
    }

    public GenericRequest(String auth, int counter,String data,String fields,Class<T> tClass,Class<S> sClass,int page) {
        super(tClass,sClass);
        this.tClass=tClass;
        this.sClass=sClass;
        this.auth=auth;
        this.counter=counter;
        this.fields=fields;
        this.page=page;
        this.data=data;
    }

    public GenericRequest(String auth, String idObject,String fields,Class<T> tClass,Class<S> sClass,int page) {
        super(tClass,sClass);
        this.tClass=tClass;
        this.sClass=sClass;
        this.auth=auth;
        this.idObject=idObject;
        this.fields=fields;
        this.page=page;
    }

    @Override
    public T loadDataFromNetwork() throws Exception {
        service=getRetrofitedInterfaceClass();
//        S obj=sClass.newInstance();
        S l=getService();
        k=l;
        if(data.equals("") && this.idObject.equals("")){
            service.getMethod("getData",String.class,int.class,String.class,int.class,int.class, Callback.class)
                    .invoke(k, auth, counter,fields, Config.AMOUNT_ELEMENTS_REQUEST,this.page, new Callback<T>() {
                        @Override
                        public void success(T ts, Response response) {
                            try {
                                objReturn = ts;
                                //tClass.getMethod("setList", Object.class).invoke(objReturn, ts);
                                finish_req = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void failure(RetrofitError retrofitError) {
                            //Log.d("Error person ", retrofitError.getMessage());
                            finish_req = true;
                        }
                    });
        }else if(data.equals("") && !this.idObject.equals("")){
            service.getMethod("getData",String.class,String.class,String.class,int.class,int.class,Callback.class)
                    .invoke(k, auth, idObject,fields, Config.AMOUNT_ELEMENTS_REQUEST,this.page, new Callback<T>() {
                        @Override
                        public void success(T ts, Response response) {
                            try {
                                objReturn = ts;
                                //tClass.getMethod("setList", Object.class).invoke(objReturn, ts);
                                finish_req = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void failure(RetrofitError retrofitError) {
                            //Log.d("Error tourmanager", retrofitError.getMessage());
                            finish_req = true;
                        }
                    });
        }
        else {
            service.getMethod("getData",String.class,int.class,String.class,String.class,int.class,int.class, Callback.class)
                    .invoke(k, auth, counter,data,fields, Config.AMOUNT_ELEMENTS_REQUEST,this.page, new Callback<T>() {
                        @Override
                        public void success(T ts, Response response) {
                            try {
                                objReturn = ts;
                                //tClass.getMethod("setList", Object.class).invoke(objReturn, ts);
                                finish_req = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void failure(RetrofitError retrofitError) {
                            //Log.d("Error person ", retrofitError.getMessage());
                            finish_req = true;
                        }
                    });
        }

        while (finish_req==false){
            Thread.sleep(100);
            //Log.d("Esperando",tClass.getSimpleName());
        }
        return objReturn;
    }
}
