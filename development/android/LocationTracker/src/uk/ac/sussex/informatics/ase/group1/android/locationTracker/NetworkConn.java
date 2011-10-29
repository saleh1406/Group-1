package uk.ac.sussex.informatics.ase.group1.android.locationTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Adapted from Open University coursework for M257, completed by:
 * @author Andy
 * .
 */
public class NetworkConn implements Runnable {

	private static final int PORT = 8080;
	//default host will be test server
	private String host = "192.168.1.2";
	private BufferedReader fromServer;
	private PrintWriter toServer;
	
	public NetworkConn() {
		
	}
	
	public NetworkConn(String hostname) {
		host = hostname;	
	}
	
	
	public void openConnection() throws IOException {
		
		Socket socket = new Socket(host, PORT);	
		
		//Create input stream for handshaking
		InputStream is = socket.getInputStream();
		fromServer = new BufferedReader(new InputStreamReader(is));
		OutputStream os = socket.getOutputStream();
		toServer = new PrintWriter(os, true);
		
	}
	
	public void closeConnection() {
		try {
			fromServer.close();
			toServer.close();
		} catch (IOException ex) {
			
		}
	}
	
	public void sendData(String theData) {
		if (toServer!=null) {
			toServer.println(theData);
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
