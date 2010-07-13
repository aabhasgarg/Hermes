package hermes;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import hermes.datastructures.Buddy;
import hermes.view.View;
import hermes.xmpp.ChatConnection;

public class Controller {

    private final View view;

    private ChatConnection conn;
    private String username = "YoFrankie";
    private String password = "testit";

    public Controller(View view) {
	this.view = view;

	this.initConnection();
	view.setBuddyList(createBuddyList());

    }

    private List<Buddy> createBuddyList() {
	System.out.println("Generating Buddylist");
	List<Buddy> bl = new ArrayList<Buddy>();
	for (RosterEntry ent : conn.getRoster().getEntries()) {
	    bl.add(new Buddy(ent.getName(), ent.getUser(), ent.getStatus()));
	    System.out.println(ent.getUser() + " " + ent.getName());
	}
	return bl;
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