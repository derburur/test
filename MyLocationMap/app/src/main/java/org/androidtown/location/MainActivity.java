package org.androidtown.location;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    GoogleMap map;
    LocationManager manager;
    MyLocationListener listener;
    SensorManager sensorManager;
    MySensorListener sensorListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = fragment.getMapAsync();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorListener = new MySensorListener();
    }

    public void reqestMyLocation(){


        long minTime = 10000;
        float minDistance = 0;

        Location lastLocation = null;


        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    minTime, minDistance, listener);

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    minTime, minDistance, listener);

        lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(lastLocation != null){
            Double latitude = lastLocation.getLatitude();
            Double longitude = lastLocation.getLongitude();

            Log.d("MainActivity", "가장 최근의 내 위치 : " + latitude + ", " + longitude);

        }
    }

    private void showCurrenMap(Double latitude, Double longitude){
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(latitude+0.001, longitude+0.001));
        marker.title("은행 지점");
        marker.snippet("잠실 지점입니다.");
        marker.draggable(true);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bank));

        map.addMarker(marker);
    }

    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            Log.d("MainActivity", "내 위치 : " + latitude + ", " + longitude);

            showCurrenMap(latitude, longitude);
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

    @Override
    protected void onResume() {
        super.onResume();
        map.setMyLocationEnabled(true);
        reqestMyLocation();
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.setMyLocationEnabled(false);
        if(manager != null){
            manager.removeUpdates(listener);
        }
        sensorManager.unregisterListener(sensorListener);
    }

    class MySensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            Log.d("MySensorListener", "sensor #0 : " + event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
