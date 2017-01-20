package org.androidtown.socketclient;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v){
        ConnectThread thread = new ConnectThread();
        thread.start();


    }

    class ConnectThread extends Thread {
        public void run() {
            String host = "192.168.25.6";
            int port = 5001;

            try {
                Socket socket = new Socket(host, port);
                System.out.println("서버로 연결됐습니다. : " + host + ", " + port);
                println("서버로 연결됐습니다. : " + host + ", " + port);

                String output = "Hello";
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(output);
                outstream.flush();
                System.out.println("서버로 보낸 데이터 : " + output);
                println("서버로 보낸 데이터 : " + output);
                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                Object input = instream.readObject();
                System.out.println("서버로부터 받은 데이터 : " + input);
                println("서버로부터 받은 데이터 : " + input);

                instream.close();
                outstream.close();
                socket.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private  void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }
}
