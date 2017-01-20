package org.androidtown.mylayoutinflate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void onButton1Clicked(View v){

        SubLayout layout1 = new SubLayout(this);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.addView(layout1);

       /*
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub_layout, container, true);

        Button button2 = (Button) container.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "부분화면", Toast.LENGTH_LONG).show();
            }
        });
        */
    }
}

