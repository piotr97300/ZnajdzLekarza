package com.example.znajdzlekarza;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.preference.PreferenceManager;
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
	        
	        String nameWithMinDistance = null;
	        float minDistance = Float.MAX_VALUE;
	        
	        while(k.moveToNext()){        	
	        	float latitude=k.getFloat(0);
	        	float longitude=k.getFloat(1);
	        	
		        locationB.setLatitude(latitude);
		        locationB.setLongitude(longitude);
		
		        float distance = locationA.distanceTo(locationB);
		        if(distance<minDistance){
		        	minDistance = distance;
		        	nameWithMinDistance = k.getString(k.getColumnIndex("name"));
		        }
	        	
	        }
	        
	        myDbHelper.close();
	        
	        strDistance += "\n" + minDistance + " m" + "\n" + "Nazwa szpitala: " + "\n" + nameWithMinDistance;
	        tvDistance.setText(strDistance);
        }
	            
    }
    
    public void shortestDistanceToDoctor(Location locationA) {
    	
	    if (locationA != null) {
	    	String strDistance = "Odleg³oœæ do najbli¿szego lekarza o specjalnosci: ";
	        double latA=locationA.getLatitude();
	        double lngA=locationA.getLongitude();
	        
	        locationA.setLatitude(latA);
	        locationA.setLongitude(lngA);
	
	        Location locationB = new Location("point B");
	        DataBaseHelper myDbHelper = new DataBaseHelper(MyApplication.getAppContext());
	        ArrayList<Float> distanceL = new ArrayList<Float>();
	        
	        Cursor k = myDbHelper.getDoctor(MainActivity.currentSpecializationId);
	        
	        String nameWithMinDistance = null;
	        String doctorTypeWithMinDistance = null;
	        String doctorNameWithMinDistance = null;
	        String doctorSurnameWithMinDistance = null;
	        float minDistance = Float.MAX_VALUE;
	        
	        while(k.moveToNext()){        	
	        	float latitude=k.getFloat(1);
	        	float longitude=k.getFloat(2);
	        	
	        	System.out.println(k.getString(0));

		        locationB.setLatitude(latitude);
		        locationB.setLongitude(longitude);
	
		        float distance = locationA.distanceTo(locationB);
		        if(distance<minDistance){
		        	minDistance = distance;
		        	nameWithMinDistance = k.getString(0);
		        	doctorTypeWithMinDistance = k.getString(5);
		        	doctorNameWithMinDistance = k.getString(3);
		        	doctorSurnameWithMinDistance = k.getString(4);
		        }
	       	
	        }
	        
	        myDbHelper.close();
	        
	        strDistance += "\n" + doctorTypeWithMinDistance + " to:" + "\n" + minDistance + " m" + "\n" + "Nazwa szpitala: " + "\n" + nameWithMinDistance + "\n" + "Imiê i nazwisko:" + "\n" + doctorNameWithMinDistance + " " + doctorSurnameWithMinDistance;
	        tvDistance.setText(strDistance);
        }
	            
    }
    
    
}
