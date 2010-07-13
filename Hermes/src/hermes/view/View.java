package hermes.view;

import hermes.Controller;
import hermes.view.jabber.JabberView;
import hermes.xmpp.ChatConnection;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * A View object includes the hole GUI of Hermes. Every change on the GUI
 * comming from the controller should pass this object
 * 
 * @author Dominic Rausch, Markus Braun
 * 
 */
public class View extends JFrame {

    public Controller controller;

    private final JabberView jabberView;

    public View() {
	this.setJMenuBar(new MenuBar());
	this.setLayout(new BorderLayout());
	this.jabberView = new JabberView(this);
	this.add(jabberView, BorderLayout.WEST);

	this.pack();
	this.setMinimumSize(this.getSize());
	this.setVisible(true);
    }

    public void setConnection(ChatConnection conn) {
	this.jabberView.setConnection(conn);
    }

    public void addChatWindow() {

    }
}
