package com.cerquitica.retrofiteasyrequest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cerquitica.retrofiteasyrequest.interfaces.TaskExecutor;
import com.cerquitica.retrofiteasyrequest.request.CategoryUpdate;
import com.cerquitica.retrofiteasyrequest.request.baseClass.ObjUpdate;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.retrofit.RetrofitSpiceService;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements TaskExecutor{

    private Context context;
    private int counter;
    private SpiceManager spiceManager = new SpiceManager(RetrofitSpiceService.class);
    private TaskExecutor executor;
    private CategoryUpdate categoryUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.context = getApplicationContext();
        //Utils.getInstance().lockScreen(context);
        spiceManager.start(this);


//        this.placeUpdate= (PlaceUpdate) intent.getExtras().getParcelable(PLACE_UPDATE);

        counter = 0;
        executor = this;


        categoryUpdate=new CategoryUpdate(spiceManager,context);



        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                NUMBER_OF_CORES*2,
                NUMBER_OF_CORES*2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );

        executorPool.execute(new Runnable() {
            public void run() {
                categoryUpdate.updateCategory(counter, executor);

            }
        });
        executorPool.execute(new Runnable() {
            public void run() {
                categoryUpdate.updateSubCategory(counter, executor);
            }
        });




    }

    @Override
    public void publishUpdate(ObjUpdate objUpdate) {

    }
}
