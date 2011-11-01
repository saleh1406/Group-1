/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package simpleservermain;

import javax.swing.*;

/**
 *
 * @author Andy
 */
public class ClientFrame extends JFrame {

    private ServerPanel sp;
    
    public ClientFrame(String title) {
	this.setTitle(title);
	this.setSize(300, 200);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	sp = new ServerPanel();
	this.add(sp);
	this.setVisible(true);
    }
    
    public void setText(String text) {
	sp.setText(text);
    }
}
