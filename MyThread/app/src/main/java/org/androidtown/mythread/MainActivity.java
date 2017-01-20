package org.androidtown.mythread;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView;
    // ResponseHandler handler = new ResponseHandler();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButtonClicked(View view){
        Log.d(TAG, "첫번째 버튼 클릭됨.");
        // textView.setText("스레드 시작");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("데이터를 저장하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("스레드 시작");
                RequestThread thread = new RequestThread();
                thread.start();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    class RequestThread extends Thread {
        public void run(){
            for(int i=0; i<100; i++){
                println("#" + i + ": 호출됨.");
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        private void println(final String data){
            Log.d(TAG, data);
            /*
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("data", data);
            message.setData(bundle);
            handler.sendMessage(message);
            */
            /*
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(data);
                }
            });
            */
            // 일정 시간 뒤에 시작
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText(data);
                }
            }, 3000);
        }
    }

    /*
    class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            textView.setText(data);
            super.handleMessage(msg);
        }
    }
    */
}
