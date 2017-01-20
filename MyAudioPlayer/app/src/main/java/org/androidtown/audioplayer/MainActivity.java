package org.androidtown.audioplayer;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
    String path = "/sdcard/recorded.mp4";
    int position;
    MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v){
        Log.d("MainActivity", "시작 버튼 클릭됨.");
        try {
            killPlayer();

            player = new MediaPlayer();
            //player.setDataSource(url);
            player.setDataSource(path);
            player.prepare();
            player.start();
        } catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "재생을 시작합니다.", Toast.LENGTH_LONG).show();
    }

    private void killPlayer(){

        if(player != null){
            player.release();
            player = null;
        }

    }

    public void onButton2Clicked(View v){
        Log.d("MainActivity", "일시정지 버튼 클릭됨.");
        if(player != null && player.isPlaying()){
            position = player.getCurrentPosition();
            player.pause();
        }
        Toast.makeText(getApplicationContext(), "재생을 일시 정지합니다.", Toast.LENGTH_LONG).show();
    }

    public void onButton3Clicked(View v){
        Log.d("MainActivity", "재시작 버튼 클릭됨.");
        if(player != null && !player.isPlaying()){
            player.start();
            player.seekTo(position);
        }
        Toast.makeText(getApplicationContext(), "재생을 재시작합니다.", Toast.LENGTH_LONG).show();
    }

    public void onButton4Clicked(View v){
        Log.d("MainActivity", "중지 버튼 클릭됨.");
        if(player != null && player.isPlaying()){
            player.stop();
        }
        Toast.makeText(getApplicationContext(), "재생을 중지합니다.", Toast.LENGTH_LONG).show();
    }

    public void onButton5Clicked(View v){
        Log.d("MainActivity", "녹음 버튼 클릭됨.");
        try {
            if(mediaRecorder != null){
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            }
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

            mediaRecorder.setOutputFile(path);
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다.", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onButton6Clicked(View v){
        if (mediaRecorder!= null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(getApplicationContext(), "녹음을 중지합니다.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killPlayer();
    }
}
