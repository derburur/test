package org.androidtown.captureintent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    File outputFile;
    FrameLayout container;
    CameraSurfaceView cameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        File storageDir = Environment.getExternalStorageDirectory();
        outputFile = new File(storageDir, "output.jpg");

        container = (FrameLayout) findViewById(R.id.container);

        cameraView = new CameraSurfaceView(this);
        container.addView(cameraView);
    }

    public void onButton1Clicked(View v){
        //단말기기 카메라 앱 실행
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));

        startActivityForResult(intent, 1001);*/

        // 서피스 뷰 사용
        cameraView.capture(new Camera.PictureCallback(){
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                imageView.setImageBitmap(bitmap);

                camera.stopPreview();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1001){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize =8;
            Bitmap bitmap = BitmapFactory.decodeFile(outputFile.getAbsolutePath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }

    class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        SurfaceHolder holder;
        Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);
            init(context);
        }

        public CameraSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        private void init(Context context){
            holder = getHolder();
            holder.addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(holder);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        public boolean capture(Camera.PictureCallback callback){
            if(camera != null) {
                camera.takePicture(null, null, callback);
                return true;
            } else {
                return false;
            }
        }
    }
}
