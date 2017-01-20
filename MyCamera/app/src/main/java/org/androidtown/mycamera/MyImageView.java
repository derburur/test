package org.androidtown.mycamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PB on 2016-10-09.
 */
public class MyImageView extends View {

    Context mContext;
    Bitmap mBitmap;
    Canvas mCanvas;
    Bitmap face;
    Paint paint;
    Camera camera = new Camera();

    public MyImageView(Context context) {
        super(context);
        init(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        paint = new Paint();
        face = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.che_small);

;    }

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

            mCanvas.drawBitmap(face, 0, 0, paint);

            camera.save();
            Matrix matrix = new Matrix();
            camera.rotateY(45.0f);
            camera.translate(0.0f, 0.0f, -3000.0f);
            camera.getMatrix(matrix);
            camera.restore();

            Bitmap rotatedFace =  Bitmap.createBitmap(face, 0, 0, face.getWidth(), face.getHeight(), matrix, true);
            mCanvas.drawBitmap(rotatedFace, 600, 300, paint);

            //mCanvas.drawColor(Color.WHITE);
            //mCanvas.drawBitmap(face, 0, 0, paint);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
