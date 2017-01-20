package org.androidtown.http;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v){
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        public void run(){
            try{
                String urlStr = editText.getText().toString();

                StringBuilder outputBuilder = new StringBuilder();

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setConnectTimeout(15000);

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if (line == null){
                            break;
                        }
                        outputBuilder.append(line + "\n");
                    }

                    reader.close();
                    conn.disconnect();
                } else {
                    System.out.println("응답코드 : " + resCode);
                }
                String output = outputBuilder.toString();
                println(output);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
                textView.append(data + "\n");
            }
        });

    }
}
