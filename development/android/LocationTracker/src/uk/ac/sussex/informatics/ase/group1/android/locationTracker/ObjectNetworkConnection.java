package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Adapted from Open University coursework for M257, completed by:
 * @author Andy
 * .
 */
public class ObjectNetworkConnection implements Runnable {

	private static final int PORT = 8082;
	//default host will be test server
	private String host = "192.168.1.2";
	private BufferedReader fromServer;
	private ObjectOutputStream objToServer;
	
	public ObjectNetworkConnection() {
		
	}
	
	public ObjectNetworkConnection(String hostname) {
		host = hostname;	
	}
	
	
	public void openConnection() throws IOException {
		
		Socket socket = new Socket(host, PORT);	
		
		//Create input stream for handshaking
		InputStream is = socket.getInputStream();
		fromServer = new BufferedReader(new InputStreamReader(is));
		OutputStream os = socket.getOutputStream();
		objToServer = new ObjectOutputStream(os);
	}
	
	public void closeConnection() {
		try {
			fromServer.close();
			objToServer.close();
		} catch (IOException ex) {
			
		}
	}
	
	public void sendObject(Object o) {
		if (objToServer!=null) {
			try {
				objToServer.writeObject(o);
			} catch (Exception e) {
				System.err.println("Unable to write object");
			}
		} else {
			System.err.println("Server is null");
		}
	}
	
	public String readResponse() {
		return "";
	}
	

	
	public void run() {
		
		try {
			openConnection();
		} catch (IOException ex) {
			
		}
		//closeConnection();
		
	}
}
