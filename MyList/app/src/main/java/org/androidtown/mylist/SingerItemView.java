package org.androidtown.mylist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by PB on 2016-10-03.
 */
public class SingerItemView extends LinearLayout {

    TextView nameTextView;
    TextView ageTextView;
    public SingerItemView(Context context) {
        super(context);
        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        ageTextView = (TextView) findViewById(R.id.nameTextView);
    }

    public void setName(String name){
        nameTextView.setText(name);
    }
    public void setAge(String age){
        ageTextView.setText(age);
    }
}
