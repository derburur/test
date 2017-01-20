package org.androidtown.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ProcessThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = new ProcessThread();
        thread.start();
    }

    public void onButton1Clicked(View v){
        thread.processHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "메인 스레드에서 새로운 스레드로 전달됨.");
            }
        });
    }

    class ProcessThread extends Thread {

        Handler processHandler = new Handler();
        public ProcessThread(){

        }
        public void run(){
            Looper.prepare();
            Looper.loop();
            for(int i=0; i<100; i++){
                Log.d(TAG, "스레드 동작중: #" + i);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
        }
    }
}
