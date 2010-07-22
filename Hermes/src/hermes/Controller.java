package hermes;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import hermes.view.View;
import hermes.xmpp.ChatConnection;

public class Controller {

    public static Controller CURRENT_INSTANCE;
    private final View view;
    private final ChatAdministration chatAdmin;
    private final DocumentAdministration docAdmin;

    public ChatConnection conn;
    public RosterEntry[] buddyList;

    private String username = "YoFrankie";
    private String password = "testit";

    public Controller(View view) {
	CURRENT_INSTANCE = this;
	this.view = view;
	this.chatAdmin = new ChatAdministration(this);
	this.docAdmin = new DocumentAdministration(this);
	this.initConnection();
	this.chatAdmin.setThisAsChatListener(conn.getChatManager());

	view.setConnection(conn);

    }

    private void initConnection() {

	try {
	    conn = new ChatConnection(username, password);
	    Presence presence = new Presence(Presence.Type.available);
	    conn.sendPacket(presence);
	    System.out.println("conn succesful");
	} catch (XMPPException e) {
	    System.out.println("conn failed");
	    e.printStackTrace();
	}
    }

    public String getThisUser() {
	return this.username;
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
	chatAdmin.initChat(buddyList[index].getUser());
    }

    public void startDocSessionWith(int user) {
	docAdmin.initDocEditing(buddyList[user].getUser());
    }

    public void editExistingFileWith(int user) {
	docAdmin.editExistingFileWith(buddyList[user].getUser());
    }

    public String getUserForNumber(int number) {
	return this.buddyList[number].getUser();
    }
}