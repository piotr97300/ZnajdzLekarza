package com.example.znajdzlekarza;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class MyLocation {
	
    private LocationManager locationManager;
    private Location savedLocation = null;

    private final TextView tvLongitude;
    private final TextView tvLatitude;
	private final TextView tvInformations;
	private final TextView tvDistance;
	
    public MyLocation(TextView tvLongitude, TextView tvLatitude, TextView tvInformations,TextView tvDistance,LocationManager locationManager) {
		super();
		this.tvLongitude = tvLongitude;
		this.tvLatitude = tvLatitude;
		this.tvInformations = tvInformations;
		this.tvDistance = tvDistance;
		this.locationManager = locationManager;
	}

    
    public LocationListener locationListener = new LocationListener() {
    	
        public void onStatusChanged(String provider, int status, Bundle extras) {}
 
        public void onProviderEnabled(String provider) {}
 
        public void onProviderDisabled(String provider) {}
 
        public void onLocationChanged(Location location) {
        	
            showLocation(location);
            showAdditionalInfo(location);
            
            Distance distance=new Distance(tvDistance);
            distance.shortestDistanceToClinic(location);
            
            if (savedLocation == null)
                savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    };
    

    private void showLocation(Location location) {
        String latitude = "Szerokoœæ geo: ";
        String longitude = "D³ugoœæ geo: ";
        if (location != null) {
            latitude += location.getLatitude();
            longitude += location.getLongitude();
            tvLatitude.setText(latitude);
            tvLongitude.setText(longitude);
        }
    }
     
    private void showAdditionalInfo(Location location) {
        String infos = "Odleg³oœæ od pierwszego fix'a: ";
        if (savedLocation == null || location == null) {
            infos += "nie mo¿na obliczyæ";
        } else {
            infos += savedLocation.distanceTo(location) + "m\n";
            infos += "Dok³adnoœæ: ";
            infos += location.getAccuracy() + "m \n";
            infos += "Prêdkoœæ: ";
            infos += location.getSpeed() + "m/s";
        }
        tvInformations.setText(infos);
    }
    

}
