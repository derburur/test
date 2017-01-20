package org.androidtown.mycomplexwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MuliCheckBox muliCheckBox = (MuliCheckBox) findViewById(R.id.multiCheck);
        muliCheckBox.setOnMultiChageListener(new MuliCheckBox.OnMultiChangeListener() {
            @Override
            public void onMultiChanged(boolean isFirstChecked, boolean isSecondChecked) {
                Toast.makeText(getApplicationContext(), "첫번째  체크 : " + isFirstChecked + "두번째 체크 : " + isSecondChecked, Toast.LENGTH_LONG).show();
            }
        });
    }
}
