package hermes.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.text.StyledDocument;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

import hermes.Constants;
import hermes.Controller;
import hermes.view.document.DocumentSessionView;

/**
 * This class represents a Session, saving all data related to one specific
 * session, such as the document itself, the collaborators, the view displaying
 * the document, the chatlog, the local and the online session name. Also
 * listens for the muc chat.
 * 
 * @author Dome
 * 
 */
public class DocSession {

    public final MultiUserChat muc;

    public String localName;
    public DocumentSessionView sessionView;

    public List<String> members;
    public String masterMember;

    public StyledDocument document;
    public MucChatModel chatLog;

    public DocSession(MultiUserChat muc) {
	this.muc = muc;
	muc.addMessageListener(new PacketListener() {

	    @Override
	    public void processPacket(Packet packet) {
		DocSession.this.mucMessageComingIn(packet);
	    }
	});
	members = new LinkedList<String>();
	chatLog = new MucChatModel();
    }

    /**
     * sends a message to the muc. If <code>message</code> is null, takes the
     * content of the textfield in the view of this muc and sends it. Does never
     * send empty messages.
     * 
     * @param message
     *            the message to send, if null, takes the content of the
     *            textfield in the view
     */
    public void sendMessage(String message) {
	if (message == null) {
	    message = sessionView.getMessageText(true);
	    sessionView.setMessageText("");
	}
	message = message.trim();
	if (message.equals("")) {
	    return;
	}
	try {
	    muc.sendMessage(message);
	} catch (XMPPException e) {
	    e.printStackTrace();
	    sessionView.setMessageText(message);
	}
    }

    /**
     * this method is called by the chatlistener if a message is incoming via
     * this muc. If its a normal chat message, prints it, else if it's a
     * controlle message, follows the instructions
     * 
     * @param p
     */
    public void mucMessageComingIn(Packet p) {
	Message m = (Message) p;
	String msg = m.getBody();
	if (msg.startsWith(Constants.CONTROLL_PREFIX)) {
	    // TODO incoming controll message
	} else {
	    chatLog.addElement((Message) p);

	}
    }

    public int getMucCount() {
	return this.chatLog.size();
    }

    public DefaultListModel getChatModel() {
	return this.chatLog;
    }

    public Message getMucMessage(int index) {
	return this.chatLog.getPacket(index);
    }

    /**
     * returns if a user is member of this session. Can be user with and without
     * the suffix @domain.org
     * 
     * @param user
     *            the username to check
     * @return if the user is member of this session
     */
    public boolean isUserInSession(String user) {
	return this.members.contains(user)
		|| members.contains(user + "@jabber.org")
		|| members.contains(user.split("@")[0]);
    }

    /**
     * invite a new user to this session
     * 
     * @param userNumber
     */
    public void inviteUser(int userNumber) {
	String userName = Controller.CURRENT_INSTANCE
		.getUserForNumber(userNumber);
	// TODO invite user

	// TODO: listen for new users and initialise them :D
    }
}
