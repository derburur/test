package org.androidtown.myselector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by PB on 2016-09-25.
 */
public class BitmapButton extends Button {
    public BitmapButton(Context context) {
        super(context);
        init();
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            setBackgroundResource(R.drawable.arw1);
        } else if (action == MotionEvent.ACTION_UP) {
            setBackgroundResource(R.drawable.arw2);
        }
        return true;
    }

    private void init(){
        setBackgroundResource(R.drawable.arw2);
    }


}
