/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;

import javax.swing.JFrame;

/**
 *
 * @author Andy
 */
public class SimpleServer {

    private static final int OBJECT_PORT = 8082;
    private static final int PRINT_PORT = 8081;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	JFrame mainFrame = new JFrame("Simple Server");
	mainFrame.setSize(250, 50);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setVisible(true);
	
	SocketListener socketListener = new SocketListener(OBJECT_PORT);
	Thread socketThread = new Thread(socketListener);
	socketThread.start();
	
	SocketListener printSocketListener = new SocketListener(PRINT_PORT);
	Thread printSocketThread = new Thread(printSocketListener);
	printSocketThread.start();
    }
}
