package org.androidtown.mysurface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MySurfaceView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = (MySurfaceView) findViewById(R.id.view1);
    }

    public void onButtonClicked(View v){
        view1.doDraw();
    }
}
