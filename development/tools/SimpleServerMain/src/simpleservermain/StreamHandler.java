/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Andy
 */
public abstract class StreamHandler implements Runnable {
       
    protected static final String newLine = System.lineSeparator();
    protected ClientFrame cf;
    
    private final int connectionNumber;
    
    private OutputStream serverReply;
    
    
    public StreamHandler(int connNumber, InputStream message, OutputStream reply) {
	connectionNumber = connNumber;
	serverReply = reply;
	cf = new ClientFrame("Device " + connectionNumber);
    }
    
    protected abstract void listen();
    
    @Override
    public void run() {
	try {
	    while(true) {
		listen();
		Thread.sleep(500);
	    }
	} catch (InterruptedException ex) {
	
	}
    }
}
