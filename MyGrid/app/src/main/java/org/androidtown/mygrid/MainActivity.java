package org.androidtown.mygrid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        SingerAdapter adapter = new SingerAdapter();
        gridView.setAdapter(adapter);
    }

    class SingerAdapter extends BaseAdapter{

        String[] names = {"소녀시대", "애프터스쿨", "걸스데이", "티아라"};
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(getApplicationContext());
            view.setText(names[position]);
            view.setTextSize(50.0f);
            view.setTextColor(Color.BLACK);
            return view;
        }
    }
}
