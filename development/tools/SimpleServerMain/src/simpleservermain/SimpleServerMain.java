/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;

/**
 *
 * @author Andy
 */
public class SimpleServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	SimpleServer ss = new SimpleServer();
	ss.listen();
    }
}
