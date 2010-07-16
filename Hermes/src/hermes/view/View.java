package hermes.view;

import hermes.Controller;
import hermes.view.jabber.ChatTab;
import hermes.view.jabber.JabberView;
import hermes.xmpp.ChatConnection;

import java.awt.BorderLayout;

import javax.swing.*;

import org.jivesoftware.smack.Chat;

/**
 * A View object includes the hole GUI of Hermes. Every change on the GUI
 * comming from the controller should pass this object
 * 
 * @author Dominic Rausch, Markus Braun
 * 
 */
public class View extends JFrame {

    public Controller controller;
    public static View CURRENT_INSTANCE;

    private final JabberView jabberView;

    public View() {
	CURRENT_INSTANCE = this;
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

    public void addChatTab(ChatTab ct) {
	jabberView.addChatTab(ct);
	this.repaint();
    }

    public void putChatTabInForeground(ChatTab ct) {
	jabberView.chatView.setSelectedComponent(ct);
    }
}
