/*
 * Adapted from Open University M257 coursework
 */
package simpleservermain;
import java.io.*;
import javax.swing.JFrame;
/**
 *
 * @author Andy
 */
public class HandleConnection implements Runnable {
    //private final BufferedReader clientMessage;
    private ObjectInputStream clientDataObject;
    private final PrintWriter serverReply;
    private final int connectionNumber;
    private ServerPanel sp;
    private static final String newLine = System.lineSeparator();
    private GPSData gpsData;
    
    public HandleConnection (int connNumber, ObjectInputStream message, PrintWriter reply) {
	connectionNumber = connNumber;
	//clientMessage = message;
	clientDataObject = message;
	serverReply = reply;
	
	JFrame jf = new JFrame();
	jf.setSize(300, 200);
	jf.setTitle("Device " + connectionNumber);
	jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	sp = new ServerPanel();
	jf.add(sp);
	jf.setVisible(true);
    }
    
    private void listen() throws IOException {
	//System.out.println("Device " + connectionNumber + ": " + clientMessage.readLine());
	//sp.setText(clientMessage.readLine() + newLine);
	try {
	    Object o = clientDataObject.readObject();
	    if (o instanceof GPSData) {
		gpsData = (GPSData) o;
		sp.setText(gpsData.toString());
	    } else if (o instanceof String) {
		sp.setText((String) o);
	    } else {
		sp.setText("Unknown object recieved");
	    }
	} catch (Exception e) {
	    
	}
    }
    
    @Override
    public void run() {
	try {
	    while(true) {
		listen();
		Thread.sleep(1000);
	    }
	} catch (IOException ex) {
	
	} catch (InterruptedException ex) {
	
	}
    }
}
