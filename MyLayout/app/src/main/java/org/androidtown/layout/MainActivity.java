package org.androidtown.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "중지버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onButton1Clicked(View v){
        Toast.makeText(getApplicationContext(), "시작버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }
}
