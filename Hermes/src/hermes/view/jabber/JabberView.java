package hermes.view.jabber;

import java.awt.event.MouseEvent;
import java.util.Collection;

import hermes.Controller;
import hermes.view.View;
import hermes.xmpp.ChatConnection;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * This class includes a BuddyListView, that displays all buddies of the current
 * XMPP connection and a ChatView, that displays alls currently opend chats
 * 
 * @author Dome
 * 
 */
public class JabberView extends JPanel {

    private final View parent;
    private final BuddyListView listView;
    public final ChatView chatView;

    private ChatConnection conn;
    private RosterEntry[] buddyList;

    public JabberView(View parent) {
	this.parent = parent;
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.listView = new BuddyListView(this);
	this.add(listView);
	this.chatView = new ChatView(this);
	this.add(chatView);

    }

    public void setConnection(ChatConnection conn) {
	this.conn = conn;
	this.conn.getRoster().addRosterListener(new RosterListener() {

	    @Override
	    public void entriesAdded(Collection<String> arg0) {
		reloadBuddyList();

	    }

	    @Override
	    public void entriesDeleted(Collection<String> arg0) {
		reloadBuddyList();

	    }

	    @Override
	    public void entriesUpdated(Collection<String> arg0) {
		reloadBuddyList();

	    }

	    @Override
	    public void presenceChanged(Presence arg0) {
		reloadBuddyList();

	    }

	});
    }

    private void reloadBuddyList() {
	if (conn != null) {
	    Object[] tmp = conn.getRoster().getEntries().toArray();
	    buddyList = new RosterEntry[tmp.length];
	    for (int i = 0; i < tmp.length; i++) {
		if (tmp[i] instanceof RosterEntry)
		    buddyList[i] = (RosterEntry) tmp[i];

	    }
	    Controller.CURRENT_INSTANCE.buddyList = buddyList;
	    this.listView.setBuddyList(buddyList);
	}
    }

    public void removeBuddy(int index) {
	try {
	    conn.getRoster().removeEntry(buddyList[index]);
	} catch (XMPPException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

    public void addBuddy() {
	if (conn != null) {
	    String JID, name = null;
	    JID = JOptionPane.showInputDialog(this, "Bitte JID eingeben",
		    "jabberID");
	    if (JID == null)
		return;
	    try {
		this.conn.getRoster().createEntry(JID, name, null);
		reloadBuddyList();
	    } catch (XMPPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    // dritter param waere die gruppe
	}

    }

    public Presence getJabberStatus(int index) {
	return conn.getRoster().getPresence(buddyList[index].getUser());

    }

    protected void showContextMenu(MouseEvent e, int person) {

    }

    public void addChatTab(ChatTab tab) {
	this.chatView.addTab(tab.getName(), tab);
    }
}
