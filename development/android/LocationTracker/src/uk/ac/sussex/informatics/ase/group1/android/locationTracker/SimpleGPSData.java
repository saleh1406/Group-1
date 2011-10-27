package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import java.io.Serializable;

public class SimpleGPSData implements Serializable {

	private String latitude;
	private String longitude;
	private int timestamp;
	
	public SimpleGPSData (String theLatitude, String theLongitude, int theTimestamp) {
		latitude = theLatitude;
		longitude = theLongitude;
		timestamp = theTimestamp;
	}
	
	
}
