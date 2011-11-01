/*
 * Adapted from Open University M257 coursework
 */
package simpleservermain;
import uk.ac.sussex.informatics.ase.group1.android.locationTracker.GPSData;
import java.io.*;

/**
 *
 * @author Andy
 */
public class ObjectStreamHandler extends StreamHandler {

    private ObjectInputStream clientDataObject;
    private GPSData gpsData;
   
    public ObjectStreamHandler (int connNumber, InputStream message, OutputStream reply) {
	super(connNumber, message, reply);
	try {
	    clientDataObject = new ObjectInputStream(message);
	} catch (IOException ex) {
	    System.err.println("Error creating connection handler: " + ex);
	}
    }
    
    @Override
    protected void listen() {
	System.out.println("Reading objects");
	try {
	    Object o = clientDataObject.readObject();
	    if (o instanceof GPSData) {
		gpsData = (GPSData) o;
		cf.setText(gpsData.toString() + newLine);
	    } else if (o instanceof String) {
		cf.setText((String) o + newLine);
	    } else {
		cf.setText("Unknown object recieved" + newLine);
	    }
	} catch (Exception e) {
	    System.err.println("Unable to set text from object " + e);
	}
    }   
}
