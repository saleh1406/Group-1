/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Andy
 */
public class SocketListener implements Runnable {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private final int PORT;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int connectionNumber = 1;
    private StreamHandler handler;
    
    public SocketListener(int port) {
	PORT = port;
	try {
	    serverSocket = new ServerSocket(PORT);
	} catch (IOException ex) {
	    System.err.println("Unable to create socket on port: " + PORT);
	    System.err.println(ex);
	}
    }
    
    @Override
    public void run() {
	while(true) {
	    System.out.println("Listening on port " + PORT);
	    try {
		socket = serverSocket.accept();
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
		if (PORT == 8082) {
		    handler = new ObjectStreamHandler(connectionNumber++, inputStream, outputStream);
		} else if (PORT == 8081) {
		    handler = new PrintStreamHandler(connectionNumber++, inputStream, outputStream);
		} else {
		    System.err.println("Warning: no connection handler for this port: " + PORT);
		}
		Thread connThread = new Thread(handler);
		connThread.start();
	    } catch (IOException ex) {
		System.err.println("Unable to create accept socket");
		System.err.println(ex);	
	    }
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException ex) {
		System.err.println(ex);
	    }
	}
    }
}
