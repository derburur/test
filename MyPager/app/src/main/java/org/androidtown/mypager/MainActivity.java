package org.androidtown.mypager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        MyAdapter adapter = new MyAdapter();
        pager.setAdapter(adapter);
    }

    public void onButtonClicked(View v){
        pager.setCurrentItem(1);
    }

    class MyAdapter extends PagerAdapter {
        String[] names = {"소녀시대", "걸스데이", "씨스타"};

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView view = new TextView(getApplicationContext());
            view.setText(names[position]);
            view.setTextSize(40.0f);
            
            layout.addView(view);
            container.addView(layout);
            return layout;
        }
    }
}
