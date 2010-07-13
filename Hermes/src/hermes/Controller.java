package hermes;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import hermes.view.View;
import hermes.xmpp.ChatConnection;

public class Controller {

    private final View view;

    public ChatConnection conn;
    private String username = "YoFrankie";
    private String password = "testit";

    public Controller(View view) {
	this.view = view;

	this.initConnection();
	view.setConnection(conn);

    }

    private void initConnection() {

	try {
	    conn = new ChatConnection(username, password);
	    System.out.println("conn succesful");
	} catch (XMPPException e) {
	    System.out.println("conn failed");
	    e.printStackTrace();
	}
    }
}