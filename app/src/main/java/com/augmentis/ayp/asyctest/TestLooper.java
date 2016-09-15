package com.augmentis.ayp.asyctest;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by Apinya on 9/14/2016.
 */
public class TestLooper extends HandlerThread {

    private static final int NUM_BER = 213;
    protected Handler handlerInThread1;
    protected Handler mHandlerMainThread;
    protected Callbacks callbacks;
    protected int i;

    public interface Callbacks{
        void convertNum(String nums);

    }

    public TestLooper(String name, Context ctx) {
        super(name);
        callbacks = (Callbacks) ctx;
    }

    public void setHandlerMainThread(Handler handlerMainThread){
        this.mHandlerMainThread = handlerMainThread;

    }

    @Override
    protected void onLooperPrepared() {

        handlerInThread1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                if(msg.what == NUM_BER){
                    int j = (int) msg.obj;
                    Log.d("Miew","Check : "+ j);

                    for(i = j; i >= 0; i--){

                        try {
                            Log.d("Miew","Check1 : "+ i);

                            Log.d("Miew","Check2 : "+ i);
                            mHandlerMainThread.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("Miew","Check3 : "+ i);
                                    callbacks.convertNum(String.valueOf(i));
                                }
                            });
                            sleep(1000); //try catch bcause it throwing from sleep

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        };


    }

    public void addMessage(Integer taget){
        Message msg = handlerInThread1.obtainMessage(NUM_BER, taget);
        msg.sendToTarget();

    }



}
