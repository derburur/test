package org.androidtown.tweenanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        container = (LinearLayout) findViewById(R.id.activity_main);
    }

    public void onButton1Clicked(View v){
        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        final Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);

        // 스케일 에니메이션이 끝나는 때를 확인하는 리스너
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //imageView.startAnimation(translate);
                container.startAnimation(translate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //imageView.startAnimation(scale);
        container.startAnimation(scale);

    }
}
