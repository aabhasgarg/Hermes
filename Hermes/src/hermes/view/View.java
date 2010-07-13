package hermes.view;

import hermes.Controller;
import hermes.view.jabber.JabberView;
import hermes.xmpp.ChatConnection;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

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

}
