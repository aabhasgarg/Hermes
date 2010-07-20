package hermes.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hermes.datastructures.DocSession;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

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

	// add border
	this.add(Box.createRigidArea(new Dimension(1, 5)));

	// add multi user chat view

	this.mucView = new JList(session.getChatModel());
	mucView.setCellRenderer(new MucListCellRenderer(session));

	JScrollPane sp = new JScrollPane(mucView);
	sp.setPreferredSize(new Dimension(700, 200));

	this.add(sp);

	// add Textfield for typing own messages
	this.messageTextField = new JTextField();
	this.add(messageTextField);
	this.messageTextField
		.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
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
