package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
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

	public static final int INSERT_ID = Menu.FIRST;
	
	private TextView latitude, longitude, gpsTime;
	
	private GPSConnection gpsConnection;
	private ObjectNetworkConnection onc;
	private PrintNetworkConnection pnc;
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message m) {
			Bundle dataBundle = m.getData();
			GPSData gpsData = (GPSData) dataBundle.getSerializable("gpsData");
	        latitude = (TextView) findViewById(R.id.user_latitude);
	        longitude = (TextView) findViewById(R.id.user_longitude); 
	        gpsTime = (TextView) findViewById(R.id.gps_time);
	        
	        latitude.setText("Latitude: " + gpsData.getLatitude());
	        longitude.setText("Longitude: " + gpsData.getLongitude());
	        gpsTime.setText("UTC: " + gpsData.getTime());
	        
	        if (onc!=null) {
	        	pnc.sendData(gpsData.toString());
	        	onc.sendObject(gpsData);
	        }
		}
	};
	
    /**
     * Find the text views and use a location manager to get location updates 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gpsConnection = new GPSConnection(this, handler);
        contactObjectServer();
        contactPrintServer();

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, INSERT_ID, 0, R.string.menu_item);
    	return result;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (INSERT_ID==item.getItemId()) {
    		//launch spinner/input to select server ip address
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	gpsConnection.startListening();

    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	gpsConnection.startListening();
    }
    
    /**
     * Remove location updates to save battery power when application not active.
     */
    @Override
    protected void onStop() {
    	super.onStop();
    	gpsConnection.stopListening();
    }
    
    private void contactObjectServer() {
    	if (isNetworkAvailable()) {
        	onc = new ObjectNetworkConnection();
        	Thread connThread = new Thread(onc);
        	connThread.start();
    	}
    }
    
    private void contactPrintServer() {
    	if (isNetworkAvailable()) {
    		pnc = new PrintNetworkConnection();
    		Thread connThread = new Thread(pnc);
    		connThread.start();
    	}
    }
    
	/**
	 * Adapted from example by
	 * @author Lars Vogel.
	 */
	public boolean isNetworkAvailable() {
		TextView netState = (TextView) findViewById(R.id.netState);
		
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // if no network is available networkInfo will be null, otherwise check if we are connected
	    if (networkInfo != null && networkInfo.isConnected()) {
	    		netState.setText("Network state: connected");
	    		return true;
	    } else {
	    	netState.setText("Network state: disconnected");
	    	return false;
	    }	    
	}
	
}