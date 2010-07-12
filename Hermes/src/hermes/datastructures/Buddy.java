package hermes.datastructures;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.RosterPacket.ItemStatus;
import org.jivesoftware.smack.packet.RosterPacket.ItemType;

public class Buddy {

    private String name;
    private String JID;
    private ItemStatus status;

    public Buddy(String name, String JID, ItemStatus status) {
	this.name = name;
	this.JID = JID;
	this.status = status;
    }

    @Override
    public String toString() {
	return this.JID;
    }
}
