package com.example.znajdzlekarza;

import android.location.Location;
import android.widget.TextView;


public class Distance {
	
	private final TextView tvDistance;
	
    public Distance(TextView tvDistance) {
		super();
		this.tvDistance = tvDistance;
	}
	
    public void showDistance(Location locationA) {
    	
	    if (locationA != null) {
	    	String strDistance = "Distance to Jan Pawel II hospital: ";
	        double latA=locationA.getLatitude();
	        double lngA=locationA.getLongitude();
	        
	        locationA.setLatitude(latA);
	        locationA.setLongitude(lngA);
	
	        Location locationB = new Location("point B");
	
	        locationB.setLatitude(50.090863);
	        locationB.setLongitude(19.938296);
	
	        float distance = locationA.distanceTo(locationB);
	        
	        strDistance += distance;
	        tvDistance.setText(strDistance);
        }
  
    }
}
