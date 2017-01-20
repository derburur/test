package org.androidtown.mypagesliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout slidingPanel;
    Button button;
    Animation left;
    Animation right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left = AnimationUtils.loadAnimation(this, R.anim.transate_left);
        right = AnimationUtils.loadAnimation(this, R.anim.transate_right);

        left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                button.setText("닫기");
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);

        slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingPanel.setVisibility(View.VISIBLE);
                slidingPanel.startAnimation(left);

            }
        });
    }
}
