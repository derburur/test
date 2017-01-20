package org.androidtown.mylist2;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    String[] names = {"소녀시대", "걸스데이", "애프터스쿨", "티아라"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }
}
