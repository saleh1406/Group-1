package simpleservermain;

import java.io.Serializable;

public class GPSData implements Serializable {

		private double latitude;
		private double longitude;
		private String time;
		
		public GPSData(double lat, double lng, String tme) {
			latitude = lat;
			longitude = lng;
			time = tme;
		}
		
		public double getLatitude() {
			return latitude;
		}
		
		public double getLongitude() {
			return longitude;
		}
		
		public String getTime() {
			return time;
		}
		
		@Override
		public String toString() {
			return latitude + ", " + longitude + ", " + time;
		}
}
