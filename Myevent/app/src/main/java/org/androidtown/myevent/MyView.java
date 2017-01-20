package org.androidtown.myevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PB on 2016-09-24.
 */
public class MyView extends View{

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.d("MyView", "손가락이 눌렸습니다.");
        } else if (event.getAction() == MotionEvent.ACTION_UP){
            Log.d("MyView", "손가락이 떼졌습니다.");
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.d("MyView", "손가락이 움직입니다.");
        }
        return true;
    }

}
