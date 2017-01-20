package org.androidtown.myanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.hello);
    }

    public void onButtonClicked(View v){
        Animation translation = AnimationUtils.loadAnimation(this, R.anim.translate);
        //textView.startAnimation(translation);
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.startAnimation(translation);
    }
}
