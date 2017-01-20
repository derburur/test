package org.androidtown.frameanimation;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    AnimationDrawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        Resources res = getResources();
        Drawable frame1 = res.getDrawable(R.drawable.happy);
        Drawable frame2 = res.getDrawable(R.drawable.shy);
        Drawable frame3 = res.getDrawable(R.drawable.joy);
        Drawable frame4 = res.getDrawable(R.drawable.angry);
        Drawable frame5 = res.getDrawable(R.drawable.love);
        Drawable frame6 = res.getDrawable(R.drawable.wink);

        int duration = 500;
        drawable = new AnimationDrawable();
        drawable.addFrame(frame1, duration);
        drawable.addFrame(frame2, duration);
        drawable.addFrame(frame3, duration);
        drawable.addFrame(frame4, duration);
        drawable.addFrame(frame5, duration);
        drawable.addFrame(frame6, duration);
        drawable.setOneShot(false);
    }

    public  void onButton1Clicked(View v){
        imageView.setBackground(drawable);
        drawable.setVisible(true, true);
        drawable.start();
    }
}
