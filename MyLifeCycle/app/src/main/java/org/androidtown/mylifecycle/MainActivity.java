package org.androidtown.mylifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplication(), "onCreate() 호출됨.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplication(), "onStop() 호출됨.", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplication(), "onDestroy() 호출됨.", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Toast.makeText(getApplication(), "onPause() 호출됨.", Toast.LENGTH_LONG).show();
        saveScore();
        super.onPause();
    }

    @Override
    protected void onStart() {
        Toast.makeText(getApplication(), "onStart() 호출됨.", Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(getApplication(), "onResume() 호출됨.", Toast.LENGTH_LONG).show();
        loadScore();
        super.onResume();
    }

    private void saveScore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score", 10000);
        editor.commit();
    }

    private  void  loadScore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE);
        int score = pref.getInt("score", 0);
        Toast.makeText(getApplication(), "읽어온 점수 : " + score, Toast.LENGTH_LONG).show();
    }
}
