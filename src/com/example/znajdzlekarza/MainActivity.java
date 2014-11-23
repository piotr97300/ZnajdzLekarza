package com.example.znajdzlekarza;


import android.content.Context;
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
    
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvProvider = (TextView) findViewById(R.id.tvProvider);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        tvDistance = (TextView) findViewById(R.id.tvDistance);
        
        String strProvider = "GPS information: ";
        tvProvider.setText(strProvider);
        tvLatitude.setText("Latitude: ");
        tvLongitude.setText("Longitude: ");
        tvInformations.setText("AdditionalInfo: ");
        tvDistance.setText("Distance to Jan Pawel II hospital: ");
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	strProvider += "GPS Enabled";
            tvProvider.setText(strProvider);
        }
        else{
        	strProvider += "GPS Disabled. Please, turn it on";
            tvProvider.setText(strProvider);
        }
       
    }
 

    @Override
    protected void onStart() {
        super.onStart();
        MyLocation mylocation=new MyLocation(tvLongitude,tvLatitude,tvInformations,tvDistance,locationManager);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000, 1, mylocation.locationListener);
    }
 
    @Override
    protected void onStop() {
        MyLocation mylocation=new MyLocation(tvLongitude,tvLatitude,tvInformations,tvDistance,locationManager);
        locationManager.removeUpdates(mylocation.locationListener);
        super.onStop();
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
