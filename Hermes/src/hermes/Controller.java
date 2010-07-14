package hermes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import hermes.view.View;
import hermes.xmpp.ChatConnection;

public class Controller {

    public static Controller CURRENT_INSTANCE;
    private final View view;

    public ChatConnection conn;
    public RosterEntry[] buddyList;

    private String username = "YoFrankie";
    private String password = "testit";

    public Controller(View view) {
	CURRENT_INSTANCE = this;
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

    private void initChatListener() {
	this.conn.getChatManager().addChatListener(new ChatManagerListener() {

	    @Override
	    public void chatCreated(Chat chat, boolean createdLocally) {
		// TODO Auto-generated method stub

	    }

	});
    }

    /**
     * starts a new chat. Creates a chat object, tells the view to add a new
     * chat to the ChatView and initialises a listener to update the view
     * 
     * @param index
     *            the index of the person to start a chat with in the buddyLisr
     *            array
     */

    public void startChatWith(int index) {
	String username = buddyList[index].getUser();

	conn.getChatManager().createChat(username, null);

    }

}