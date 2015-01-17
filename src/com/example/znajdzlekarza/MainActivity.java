package com.example.znajdzlekarza;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	

	private Button btClinic;
	private Button btDoctor;
    private ListView list ; 
    private ArrayAdapter<String> adapter ;
	public static int currentSpecializationId;
	public static boolean currentFindClinicActivity = false; 
	public static boolean currentFindDoctorActivity = false; 
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btClinic=(Button) findViewById(R.id.btClinic);
        btDoctor=(Button) findViewById(R.id.btDoctor);
        
        btClinic.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v1) {
    			currentFindClinicActivity = true;
    			currentFindDoctorActivity = false;
    			Intent nextScreen1 = new Intent(MainActivity.this, FindClinicActivity.class);
    			startActivity(nextScreen1);
    		}
    	});
        
        btDoctor.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v1) {
    			currentFindDoctorActivity = true;
    			currentFindClinicActivity = false;
    			Intent nextScreen1 = new Intent(MainActivity.this, FindDoctorActivity.class);
    			startActivity(nextScreen1);
    		}
    	});
        
        list = (ListView) findViewById(R.id.listView1);
        
        DataBaseHelper myDbHelper = new DataBaseHelper(this);

        try {
        	 
        	myDbHelper.createDataBase();
        	 
        	} catch (IOException ioe) {
        	 
        	throw new Error("Unable to create database");
        	 
        	}
        
        try {
        	 
        	myDbHelper.openDataBase();
        	 
        	}catch(SQLException sqle){
        	 
        	throw sqle;
        	 
        	}
        
        ArrayList<String> specializationsL = new ArrayList<String>();
        
        Cursor k = myDbHelper.getSpecializations();
        while(k.moveToNext()){
        	String specialization=k.getString(0);
        	specializationsL.add(specialization);
        }

        myDbHelper.close();
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,specializationsL);
        list.setAdapter(adapter);
        
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
  
              	MainActivity.currentSpecializationId=position;
              	
             }
        };
            
        list.setOnItemClickListener(itemClickListener);
           
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
