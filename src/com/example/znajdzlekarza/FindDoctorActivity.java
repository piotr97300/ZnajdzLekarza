package com.example.znajdzlekarza;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FindDoctorActivity extends ActionBarActivity {

    private TextView tvProvider;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvInformations;
    private TextView tvDistance;
    private Button btNavigate;
    
    private Button btBack;
 
    private LocationManager locationManager;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        tvProvider = (TextView) findViewById(R.id.tvProvider);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        tvDistance = (TextView) findViewById(R.id.tvDistance);
        
        btBack = (Button) findViewById(R.id.btBack);
        btNavigate = (Button) findViewById(R.id.btNavigate);
        
        String strProvider = "Informacje o GPS: ";
        tvProvider.setText(strProvider);
        tvLatitude.setText("Szerokoœæ geo:");
        tvLongitude.setText("D³ugoœæ geo: ");
        tvInformations.setText("Dodatkowe info: ");
        tvDistance.setText("Odleg³oœæ do najbli¿szego lekarza: ");

        btBack.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v1) {
    			MainActivity.currentFindDoctorActivity = false;
    			Intent nextScreen1 = new Intent(FindDoctorActivity.this, MainActivity.class);
    			startActivity(nextScreen1);
    		}
    	});
        
        btNavigate.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v2) {
    			MainActivity.currentFindClinicActivity = false;
    			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
    		    Uri.parse("google.navigation:q="+Distance.latitudeWithMinDistanceToDoctor+","+Distance.longitudeWithMinDistanceToDoctor));
    			startActivity(intent);
    		}
    	});
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	strProvider += "GPS w³¹czony";
            tvProvider.setText(strProvider);
        }
        else{
        	strProvider += "GPS wy³¹czony. Proszê w³¹czyæ GPS.";
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
