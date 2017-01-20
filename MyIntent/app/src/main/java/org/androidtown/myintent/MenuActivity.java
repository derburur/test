package org.androidtown.myintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        if(intent != null){
            String title = intent.getStringExtra("title");
            int age = intent.getIntExtra("age", 0);
            Person person1 = (Person) intent.getSerializableExtra("person");
            Toast.makeText(getApplicationContext(), "title : " + person1.getName(), Toast.LENGTH_LONG).show();
        }
    }
}
