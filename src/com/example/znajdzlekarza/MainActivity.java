package com.example.znajdzlekarza;


import java.util.Date;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
    private TextView tvProvider;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvInformations;
    private TextView tvDistance;
 
    private LocationManager locationManager;
    private Location savedLocation = null;
    
    private LocationListener locationListener = new LocationListener() {
    	
        public void onStatusChanged(String provider, int status, Bundle extras) {}
 
        public void onProviderEnabled(String provider) {}
 
        public void onProviderDisabled(String provider) {}
 
        public void onLocationChanged(Location location) {
        	
            showLocation(location);
            showAdditionalInfo(location);
            showDistance(location);
            if (savedLocation == null)
                savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    };
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvProvider = (TextView) findViewById(R.id.tvProvider);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        tvDistance = (TextView) findViewById(R.id.tvDistance);
        
        tvLatitude.setText("Latitude: ");
        tvLongitude.setText("Longitude: ");
        tvInformations.setText("AdditionalInfo: ");
        tvDistance.setText("Distance to Jan Pawel II hospital: ");
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            tvProvider.setText("GPS");
        else
            tvProvider.setText("GPS Disabled. Please, turn it on");
     
       
    }
 
    private void showDistance(Location locationA) {
    	
	    if (locationA != null) {
	    	String stringDistance = "Distance to Jan Pawel II hospital: ";
	        double latA=locationA.getLatitude();
	        double lngA=locationA.getLongitude();
	        
	        locationA.setLatitude(latA);
	        locationA.setLongitude(lngA);
	
	        Location locationB = new Location("point B");
	
	        locationB.setLatitude(50.090863);
	        locationB.setLongitude(19.938296);
	
	        float distance = locationA.distanceTo(locationB);
	        
	        stringDistance += distance;
	        tvDistance.setText(stringDistance);
        }
  
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000, 1, locationListener);
    }
 
    @Override
    protected void onStop() {
        locationManager.removeUpdates(locationListener);
        super.onStop();
    }
            
    private void showLocation(Location location) {
        String latitude = "Latitude: ";
        String longitude = "Longitude: ";
        if (location != null) {
            latitude += location.getLatitude();
            longitude += location.getLongitude();
            tvLatitude.setText(latitude);
            tvLongitude.setText(longitude);
        }
    }
     
    private void showAdditionalInfo(Location location) {
        String infos = "Distance from first fix: ";
        if (savedLocation == null || location == null) {
            infos += "can't calculate";
        } else {
            infos += savedLocation.distanceTo(location) + "m\n";
            infos += "Accuracy: ";
            infos += location.getAccuracy() + "m \n";
            infos += "Last fix: ";
            infos += new Date(location.getTime()).toGMTString() + "\n";
            infos += "Speed: ";
            infos += location.getSpeed() + "m/s";
        }
        tvInformations.setText(infos);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
