package hermes.datastructures;

import javax.swing.DefaultListModel;

import org.jivesoftware.smack.packet.Message;

public class MucChatModel extends DefaultListModel {
    @Override
    public Object getElementAt(int index) {

	Message m = (Message) super.getElementAt(index);
	String from = m.getFrom().split("/")[1];
	return from + ": " + m.getBody();
    }

    public Message getPacket(int index) {
	return (Message) super.getElementAt(index);
    }
}
