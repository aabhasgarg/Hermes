package hermes.view.jabber;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class ChatTab extends JPanel implements MessageListener {

    private final Chat chat;
    private final DefaultListModel data;

    private final JList messageListView;
    private final JTextField messageField;
    private final JButton sendButton;

    public ChatTab(Chat chat) {
	this.chat = chat;
	chat.addMessageListener(this);
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	data = new DefaultListModel();

	this.messageListView = new JList(data);
	this.messageListView.setMinimumSize(new Dimension(100, 250));

	ScrollPane sp = new ScrollPane();
	sp.add(messageListView);
	sp.setMinimumSize(new Dimension(100, 250));
	this.add(sp);

	messageField = new JTextField();
	this.add(messageField);
	this.messageField.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
	sendButton = new JButton("Send");
	sendButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ChatTab.this.sendMessage(null);
	    }

	});
	this.add(sendButton);
    }

    @Override
    public void processMessage(Chat chat, Message message) {
	synchronized (this) {
	    receiveMessage(message);
	}
    }

    public String getName() {
	return chat.getParticipant();
    }

    private void sendMessage(String message) {
	if (message == null) {
	    message = messageField.getText();
	    if (message.trim().equals("")) {
		return;
	    }
	}

	try {
	    chat.sendMessage(message);
	    data.addElement(message);
	    messageField.setText("");
	} catch (XMPPException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private void receiveMessage(Message message) {
	data.addElement(message.getBody());
	System.out.println(message.getBody());
	this.repaint();

    }
}
