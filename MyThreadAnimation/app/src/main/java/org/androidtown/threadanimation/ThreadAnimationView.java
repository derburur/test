package org.androidtown.threadanimation;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by PB on 2016-10-30.
 */

public class ThreadAnimationView extends ImageView {
    int[] imageArray = {R.drawable.happy, R.drawable.angry, R.drawable.joy, R.drawable.wink, R.drawable.love, R.drawable.shy};
    Handler handler = new Handler();
    public ThreadAnimationView(Context context) {
        super(context);
        init(context);
    }

    public ThreadAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        ImageThread thread = new ImageThread();
        thread.start();
    }

    class ImageThread extends Thread {
        boolean running = false;
        int index = 0;
        int interval = 500;

        public void run() {
            running = true;
            while(running){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setImageResource(imageArray[index]);
                        invalidate();
                    }
                });

                try {
                    Thread.sleep(interval);
                }catch(Exception e){
                    e.printStackTrace();
                }
                index++;
                if(index > imageArray.length - 1){
                    index = 0;
                }
            }
        }
    }
}
