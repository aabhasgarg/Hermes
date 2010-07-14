package hermes.view.jabber;

import javax.swing.JPanel;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class ChatTab extends JPanel implements MessageListener {

    public ChatTab(Chat chat) {

    }

    @Override
    public void processMessage(Chat arg0, Message arg1) {
	// TODO Auto-generated method stub

    }

}
