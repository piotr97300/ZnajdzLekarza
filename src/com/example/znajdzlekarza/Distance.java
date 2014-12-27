package com.example.znajdzlekarza;

import java.util.ArrayList;
import java.util.Collections;

import android.database.Cursor;
import android.location.Location;
import android.widget.TextView;


public class Distance{
	
	private final TextView tvDistance;
	
    public Distance(TextView tvDistance) {
		super();
		this.tvDistance = tvDistance;
	}
	
    public void shortestDistanceToClinic(Location locationA) {
    	
	    if (locationA != null) {
	    	String strDistance = "Odleg³oœæ do najbli¿szego szpitala: ";
	        double latA=locationA.getLatitude();
	        double lngA=locationA.getLongitude();
	        
	        locationA.setLatitude(latA);
	        locationA.setLongitude(lngA);
	
	        Location locationB = new Location("point B");
	        DataBaseHelper myDbHelper = new DataBaseHelper(MyApplication.getAppContext());
	        ArrayList<Float> distanceL = new ArrayList<Float>();
	        
	        Cursor k = myDbHelper.getCoordinates();
	        while(k.moveToNext()){        	
	        	float latitude=k.getFloat(1);
	        	float longitude=k.getFloat(0);
	        	
		        locationB.setLatitude(latitude);
		        locationB.setLongitude(longitude);
		
		        float distance = locationA.distanceTo(locationB);
		        
	        	distanceL.add(distance);
	        	
	        }
	        
	        myDbHelper.close();
	        
	        float minDistance = Collections.min(distanceL);
	        //System.out.println(minDistance);
	        
	        //TODO 1
	        //Trzeba jakoœ zrobiæ aby wyœwietla³ te¿ nazwê szpitala
	        //Tak sobie mysle,ze trzeba pokombinowac z jak¹œ list¹ wielowymiarow¹
	        
	        strDistance += minDistance + " m";
	        tvDistance.setText(strDistance);
        }
	    
    }
}
