package org.androidtown.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    int value = 0;
    BackgroundTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onButton1Clicked(View v){
        task = new BackgroundTask();
        task.execute(100);
    }

    public void onButton2Clicked(View v){
        task.cancel(true);
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            value=0;
            progressBar.setProgress(value);
            textView.setText("중지됨.");
        }

        @Override
        protected void onPreExecute() {
            value=0;
            progressBar.setProgress(value);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            textView.setText("Status : " + values[0]);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(!isCancelled()){
                value++;
                if(value >= 100){
                    break;
                }else{
                    publishProgress(value);
                }

                try{
                    Thread.sleep(200);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return value;
        }
    }
}
