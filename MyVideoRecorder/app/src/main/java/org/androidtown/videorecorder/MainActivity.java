package org.androidtown.videorecorder;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    SurfaceView surfaceView;
    SurfaceHolder holder;

    MediaRecorder recorder;
    String path = "/sdcard/recorded_video.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
    }

    public void onButton1Clicked(View v){
        try {
            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            recorder.setOutputFile(path);

            recorder.setPreviewDisplay(holder.getSurface());
            recorder.start();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "녹화가 시작됐습니다.", Toast.LENGTH_LONG).show();
    }

    public void onButton2Clicked(View v){
        if(recorder != null){
            recorder.stop();
            recorder.release();
            recorder = null;
        }
        Toast.makeText(getApplicationContext(), "녹화가 중지됐습니다.", Toast.LENGTH_LONG).show();
    }
}
