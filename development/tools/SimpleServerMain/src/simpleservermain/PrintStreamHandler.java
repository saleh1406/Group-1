/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;

import java.io.*;


/**
 *
 * @author Andy
 */
public class PrintStreamHandler extends StreamHandler {
    
    private BufferedReader clientMessage;
    
    public PrintStreamHandler(int connNumber, InputStream message, OutputStream reply) {
	super(connNumber, message, reply);
	clientMessage = new BufferedReader(new InputStreamReader(message));	
    }
    
    @Override
    protected void listen() {
	System.out.println("Reading string from client");
	try {
	    cf.setText(clientMessage.readLine() + newLine);
	} catch (IOException ex) {
	    System.err.println(ex);
	}
    }
}
