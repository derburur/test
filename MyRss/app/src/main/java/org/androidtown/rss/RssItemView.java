package org.androidtown.rss;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by PB on 2016-12-28.
 */

public class RssItemView extends LinearLayout{

    ImageView imageView;
    TextView textView;
    TextView textView2;
    TextView textView3;

    public RssItemView(Context context) {
        super(context);
        init(context);
    }

    public RssItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rss_item, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
    }

    public void setRssItem(RssItem item){
        String title = item.getTitle();
        textView.setText(title);

        String dcDate = item.getDcDate();
        textView2.setText(dcDate);

        String description = item.getDescription();
        textView3.setText(description);

    }
}
