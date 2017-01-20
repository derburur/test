package org.androidtown.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class ManuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu );

        Intent intent = getIntent();
        if(intent !=  null){
            String title = intent.getStringExtra("title");
            Toast.makeText(getApplicationContext(), "전달받은 값은 :" + title, Toast.LENGTH_LONG).show();
        }
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent();
        intent.putExtra("name", "Tiara");
        setResult(RESULT_OK, intent);
        finish();  // close view
    }
}
