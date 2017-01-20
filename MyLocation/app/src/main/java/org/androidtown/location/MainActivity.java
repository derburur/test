package org.androidtown.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long minTime = 10000;
        float minDistance = 0;
        MyLocationListener listener = new MyLocationListener();
        Location lastLocation = null;

        try {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    minTime, minDistance, listener);

            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    minTime, minDistance, listener);

            lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(lastLocation != null){
            Double latitude = lastLocation.getLatitude();
            Double longitude = lastLocation.getLongitude();

            textView.setText("가장 최근의 내 위치 : " + latitude + ", " + longitude);
            textView.invalidate();
        }
    }

    class MyLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            textView.setText("내 위치 : " + latitude + ", " + longitude);
            textView.invalidate();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
