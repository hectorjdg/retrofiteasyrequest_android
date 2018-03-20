package com.cerquitica.retrofiteasyrequest.request.baseClass;

import android.content.Context;

import com.cerquitica.retrofiteasyrequest.interfaces.IProperExecuteSpice;
import com.cerquitica.retrofiteasyrequest.interfaces.TaskExecutor;
import com.j256.ormlite.dao.Dao;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alquimista on 3/4/2016.
 */
public class GetAllDataGeneric<P, C, D> {
    private  SpiceManager spiceManager;

    private Context context;

    private boolean completed;
    private int cant_ent;
    private String CONS_CLASS = "BY_DEFINED";
    private int countTaskCompleted = 0;
    private  volatile Dao<D, UUID> dao;
    private  volatile List<D> list = null;
    private String token="token to write";
    private String auth = "Bearer " + token;

    public GetAllDataGeneric() {
    }

    public GetAllDataGeneric(String CONS_CLASS, Context context, int cant_ent) {
        this.CONS_CLASS = CONS_CLASS;
        this.cant_ent = cant_ent;
        this.context = context;
        this.spiceManager=getSpiceManager();
    }

    public GetAllDataGeneric(SpiceManager spiceManager, String CONS_CLASS, Context context, int cant_ent) {
        this.spiceManager = spiceManager;
        this.CONS_CLASS = CONS_CLASS;
        this.context = context;
        this.cant_ent = cant_ent;
    }

    private void doProgressUpdate(TaskExecutor executor) {
        countTaskCompleted++;
        if (executor != null) {
            if (countTaskCompleted == cant_ent) {
                completed = true;
                //Log.d("Terminado", CONS_CLASS);
            }
            executor.publishUpdate(ObjUpdate.getInstance(CONS_CLASS, countTaskCompleted, completed));
            if (countTaskCompleted == cant_ent) {
                countTaskCompleted = 0;
            }
            completed = false;
        }
    }

    public LinkedList<D> updateWebServiceSimple(final IProperExecuteSpice properExecuteSpice, final String data, final Class<P> objParser, final Class<C> objFullCall, final Class<D> objModel, final TaskExecutor executor, final int counter, final int cant_elements, final int passes) {
        if (cant_elements <= 0) {
            doProgressUpdate(executor);
        } else {
            String fields = Utils.getInstance().getAllFieldsFromClass(objModel);
            GenericRequest request;

            if (properExecuteSpice != null && data != null) {
                request = new GenericRequest<>(auth, counter, data, fields,
                        objParser,
                        objFullCall, passes
                );

            } else {
                request = new GenericRequest<>(auth, counter, fields,
                        objParser,
                        objFullCall, passes
                );
            }


            getSpiceManager().execute(request, new RequestListener() {
                        @Override
                        public void onRequestFailure(SpiceException e) {
                           e.printStackTrace();
                        }

                        @Override
                        public void onRequestSuccess(Object obj) {

                            try {
                                list = (List<D>) obj.getClass().getDeclaredField("list").get(obj);
                                if (properExecuteSpice != null) {
                                    properExecuteSpice.execute(list);
                                }
                            } catch (Exception e) {

                            }
                            try {
                                if (list != null) {
                                    dao = SingletonBD.getInstance(context).getDbHelper().getModelActivity(objModel);

                                    for (D rolTemp : list) {
                                        dao.createOrUpdate(rolTemp);
                                    }
                                    try {
                                        int number = (int) obj.getClass().getDeclaredField("number").get(obj);

                                        int temCant_elements = number - passes * Config.AMOUNT_ELEMENTS_REQUEST;
                                        int temPasses = passes + 1;
                                        updateWebServiceSimple(properExecuteSpice, data, objParser, objFullCall, objModel, executor, counter, temCant_elements, temPasses);

                                    } catch (Exception e) {

                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (cant_elements < 0) {
                                doProgressUpdate(executor);
                            }
                        }
                    }
            );
        }

        return null;
    }
    
    public LinkedList<D> updateWebServiceReturn(final IProperExecuteSpice properExecuteSpice, final String data, final Class<P> objParser, final Class<C> objFullCall, final Class<D> objModel, final TaskExecutor executor, final int counter, final int cant_elements, final int passes) {

        if (cant_elements <= 0) {
            doProgressUpdate(executor);
        } else {
            String fields = Utils.getInstance().getAllFieldsFromClass(objModel);
            GenericRequest request;

            if (data != null) {
                request = new GenericRequest<>(auth, counter, data, fields,
                        objParser,
                        objFullCall, passes
                );

            } else {
                request = new GenericRequest<>(auth, counter, fields,
                        objParser,
                        objFullCall, passes
                );
            }
            getSpiceManager().execute(request, new RequestListener() {
                        @Override
                        public void onRequestFailure(SpiceException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onRequestSuccess(Object obj) {
                            try {
                                list = (List<D>) obj.getClass().getDeclaredField("list").get(obj);
                                if (properExecuteSpice != null) {
                                    properExecuteSpice.execute(list);
                                }
                            } catch (Exception e) {

                            }
                            try {
                                if (list != null) {
//
                                    try {
                                        int number = (int) obj.getClass().getDeclaredField("number").get(obj);

                                        int temCant_elements = number - passes * Config.AMOUNT_ELEMENTS_REQUEST;
                                        int temPasses = passes + 1;
                                        updateWebServiceReturn(properExecuteSpice, data, objParser, objFullCall, objModel, executor, counter, temCant_elements, temPasses);

                                    } catch (Exception e) {

                                    }

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (cant_elements < 0) {
                                doProgressUpdate(executor);
                            }
                        }
                    }
            );
        }
        return null;
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }



}
