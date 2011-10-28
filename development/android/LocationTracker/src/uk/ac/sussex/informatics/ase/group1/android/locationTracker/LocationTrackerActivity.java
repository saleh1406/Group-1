package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import java.text.DateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Prints the users location, latitude and longitude, as well as the GPS time in UTC format in a simple
 * linear layout activity..
 * 
 * @author Andy Keavey
 * @version 0.01
 *
 */
public class LocationTrackerActivity extends Activity {
	
	private LocationListener ll;
	private LocationManager lm;
	
	private TextView latitude, longitude, gpsTime;
	private CharSequence latitudeText, longitudeText;
	private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	private TimeZone tz = TimeZone.getTimeZone("UTC");
	
	private NetworkConn nc;
	
    /**
     * Find the text views and use a location manager to get location updates 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
         * To support multiple languages, we get the string set by the String resource file..
         */
        latitude = (TextView) findViewById(R.id.user_latitude);
        longitude = (TextView) findViewById(R.id.user_longitude); 
        gpsTime = (TextView) findViewById(R.id.gps_time);       
        latitudeText = latitude.getText();
        longitudeText = longitude.getText();
        
	    lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);    
	    ll = new GPSLocationListener(); 
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
		
		dateFormat.setTimeZone(tz);
		//isNetworkAvailable();
    	nc = new NetworkConn();
    	Thread connThread = new Thread(nc);
    	connThread.start();
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	//do nothing

    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	//lm.removeUpdates(ll);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
    }
    
    /**
     * Remove location updates to save battery power when application not active.
     */
    @Override
    protected void onStop() {
    	super.onStop();
    	lm.removeUpdates(ll);
    	//nc.closeConnection();
    }
    
    /*
     * The setText method must be called from within the Activity class containing the target TextView
     * This code could be put in a handler.
     * We use the GPS time (UTC) as it will be standard in all locations, system time is displayed on the
     * phone's status bar anyway..
     */
    private void showLocation(Location location) {    
        latitude.setText(latitudeText + " " + location.getLatitude());
        longitude.setText(longitudeText + " " + location.getLongitude());
        gpsTime.setText("UTC: " + dateFormat.format(location.getTime()));
    }
    
	/*
	 * Adapted from example by
	 * @author Lars Vogel.
	 */
	public void isNetworkAvailable() {
		String text;
	    //ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    //NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // if no network is available networkInfo will be null, otherwise check if we are connected
	    //if (networkInfo != null && networkInfo.isConnected()) {
	    if (true) {
		text = "Connected";
	    } else {
	    	text = "No connection";
	    }
	    TextView netState = (TextView) findViewById(R.id.netState);
	    netState.setText("Network state: " + text);
	    
	}
	
    /**
     * Inner class responsible for getting information about the users location.
     * @author Andy
     *.
     */
    class GPSLocationListener implements LocationListener {
    	public void onLocationChanged(Location location) {
    		showLocation(location);

    	}
    	
    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		
    	}
    	
    	public void onProviderEnabled(String provider) {
    		
    	}
    	
    	public void onProviderDisabled(String provider) {
    		
    	}
    } //end inner class GPSLocationListener
}