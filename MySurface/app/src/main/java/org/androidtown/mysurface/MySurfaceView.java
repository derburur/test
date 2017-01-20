package org.androidtown.mysurface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by PB on 2016-10-09.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    SurfaceHolder holder;
    Paint paint;
    Context mContext;

    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        this.holder = getHolder();
        this.holder.addCallback(this);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10.0f);
    }

    public void doDraw(){
        Toast.makeText(mContext, "doDraw() 호출됨.", Toast.LENGTH_LONG).show();
        invalidate();
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Toast.makeText(mContext, "onDraw() 호출됨.", Toast.LENGTH_LONG).show();
        canvas.drawRect(100, 100, 200, 200, paint);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
