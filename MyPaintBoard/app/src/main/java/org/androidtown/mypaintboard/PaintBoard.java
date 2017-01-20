package org.androidtown.mypaintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PB on 2016-10-09.
 */
public class PaintBoard extends View {

    Context mContext;
    Paint paint;
    Bitmap mBitmap;
    Canvas mCanvas;

    float oldX, oldY, curX, curY;

    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap != null){
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w>0 && h>0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas();
            mCanvas.setBitmap(mBitmap);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        curX = event.getX();
        curY = event.getY();

        if (action == MotionEvent.ACTION_DOWN){

        } else if (action == MotionEvent.ACTION_MOVE){
            mCanvas.drawLine(oldX, oldY, curX, curY, paint);
        } else if (action == MotionEvent.ACTION_UP){

        }

        invalidate();

        oldX = curX;
        oldY = curY;

        return true;
    }
}
