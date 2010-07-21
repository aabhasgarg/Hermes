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

    public void sendMessage(String message) {
	if (message == null) {
	    message = sessionView.getMessageText();
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

    public void mucMessageComingIn(Packet p) {
	chatLog.addElement((Message) p);
	sessionView.updateMuc();
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

    public boolean isUserInSession(String user) {
	return this.members.contains(user);
    }
}
