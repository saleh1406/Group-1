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
public class ServerPanel extends JPanel {
    private JTextArea jt;
    
    public ServerPanel() {
	jt = new JTextArea();
	this.add(jt);
    }
    
    public void setText(String text) {
	jt.append(text);
    }
}
