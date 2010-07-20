package hermes.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hermes.datastructures.DocSession;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import javax.swing.JPanel;

import org.jivesoftware.smack.packet.Message;

public class DocumentSessionView extends JPanel {

    private final DocSession session;

    private final JTextPane textView;
    private final JList mucView;
    private final JTextField messageTextField;

    public DocumentSessionView(DocSession s) {
	this.session = s;
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	// add editor view
	textView = new JTextPane();
	this.add(textView);

	// add multi user chat view
	this.mucView = new JList();
	this.add(mucView);
	this.mucView.setModel(new AbstractListModel() {

	    @Override
	    public Object getElementAt(int index) {
		Message m = (Message) session.getMucPacket(index);
		return m.getFrom() + ": " + m.getBody();
	    }

	    @Override
	    public int getSize() {
		return session.getMucCount();
	    }

	});
	mucView.setEnabled(false);
	mucView.setCellRenderer(new MucListCellRenderer(session));

	// add Textfield for typing own messages
	this.messageTextField = new JTextField();
	this.add(messageTextField);
	this.messageTextField.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		DocumentSessionView.this.session.sendMessage(null);
	    }

	});

	// add send button
	JButton sendButton = new JButton("Send");
	this.add(sendButton);
	sendButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		DocumentSessionView.this.session.sendMessage(null);
	    }

	});

    }

    public String getLocalName() {
	return session.localName;
    }

    public String getMessageText() {
	return this.messageTextField.getText();
    }

    public void setMessageText(String newText) {
	this.messageTextField.setText(newText);

    }

    public void updateMuc() {

    }
}
