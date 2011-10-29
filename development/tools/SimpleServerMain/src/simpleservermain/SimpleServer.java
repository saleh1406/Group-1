/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;

/**
 *
 * @author Andy
 */
public class SimpleServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private static final int PORT = 8080;
    private BufferedReader fromClient;
    private PrintWriter toClient;
    private int clientNumber = 0;
    
    public SimpleServer() {
	try {
	    serverSocket = new ServerSocket(PORT);
	} catch (IOException ex) {
	    
	}	
	JFrame mainFrame = new JFrame("Simple Server");
	mainFrame.setSize(250, 50);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setVisible(true);
    }
        
    private void openConnection() throws IOException {
        InputStream is = socket.getInputStream();
        fromClient = new BufferedReader(new InputStreamReader(is));
        OutputStream os = socket.getOutputStream();
        toClient = new PrintWriter(os, true);
        clientNumber++; //if exception is thrown, this is not reached
    }
    
    /*
     * Listen for new connections from clients
     */
    public void listen() {
	while(true) {
	    try {
		socket = serverSocket.accept();
		openConnection();
		HandleConnection handle = new HandleConnection(clientNumber, fromClient, toClient);
		Thread connThread = new Thread(handle);
		connThread.start();
		Thread.sleep(1000);
	    } catch (IOException ex) {
		
	    } catch (InterruptedException ex) {
		
	    }
	}
    }
}
