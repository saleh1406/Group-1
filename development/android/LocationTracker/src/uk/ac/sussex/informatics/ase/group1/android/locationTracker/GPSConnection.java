package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import java.text.DateFormat;
import java.util.TimeZone;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GPSConnection {

	private LocationManager lm;
	private LocationListener ll;
	private GPSData gpsData;
	private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	private TimeZone tz = TimeZone.getTimeZone("UTC");
	private Handler handler;
	
	public GPSConnection(Context context, Handler aHandler) {
		handler = aHandler;
	    lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);    
	    ll = new GPSLocationListener(); 
		
		dateFormat.setTimeZone(tz);
	}
	
	public void startListening() {
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
	}
	
	public void stopListening() {
		lm.removeUpdates(ll);
	}
	
	private void setLocation(Location location) {
		gpsData = new GPSData(location.getLatitude(), location.getLongitude(), dateFormat.format(location.getTime()));
		Bundle gpsBundle = new Bundle();
		gpsBundle.putSerializable("gpsData", gpsData);
		Message gpsMessage = Message.obtain();
		gpsMessage.setData(gpsBundle);
		handler.sendMessage(gpsMessage);
	}
	
	
    /**
     * Inner class responsible for getting information about the users location.
     * @author Andy
     *.
     */
    class GPSLocationListener implements LocationListener {
    	public void onLocationChanged(Location location) {
    		setLocation(location);
    	}
    	
    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		
    	}
    	
    	public void onProviderEnabled(String provider) {
    		
    	}
    	
    	public void onProviderDisabled(String provider) {
    		
    	}
    } //end inner class GPSLocationListener
}
