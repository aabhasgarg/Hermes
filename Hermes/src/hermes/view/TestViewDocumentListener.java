package hermes.view;

import hermes.protocol.ActionsInterface;
import hermes.protocol.InProtocol;
import hermes.protocol.OutProtocol;
import hermes.xmpp.ChatConnection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class TestViewDocumentListener extends JFrame implements
		ActionsInterface {

	private ChatConnection conn;
	private OutProtocol outProtocol;
	private InProtocol inProtocol;
	private String username = "Isodome";
	private String password = "testit";
	private MultiUserChat muc;
	private JTextArea text;

	public TestViewDocumentListener() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		setSize(800, 400);
		initConnection();
		if (conn == null)
			System.exit(0);
		initMultiuserChat();
		initProtocols();
		addMultiuserListener();
	}

	private void initProtocols() {
		outProtocol = new OutProtocol(muc, username);
		inProtocol = new InProtocol(this, username);
	}

	private void addMultiuserListener() {
		muc.addMessageListener(new PacketListener() {

			@Override
			public void processPacket(Packet pack) {
				inProtocol.parse((Message) pack);
			}
		});
	}

	private void initMultiuserChat() {
		muc = new MultiUserChat(conn, "bbblablubbernewroom@conference.jabber.org");
		try {
			muc.join(username);

		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	private void initConnection() {

		try {
			conn = new ChatConnection(username, password);
			System.out.println("conn succesful");
		} catch (XMPPException e) {
			System.out.println("conn failed");
			e.printStackTrace();
		}
	}

	private void initComponents() {
		text = new JTextArea();
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int selStart = text.getSelectionStart();
				String typed = String.valueOf(e.getKeyChar());
				if (typed.equals("\n"))
					selStart--;
				outProtocol.addSign(0, selStart, typed);
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		add(text);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestViewDocumentListener().setVisible(true);
	}

	@Override
	public void addLine(int lineNumb) {

	}

	@Override
	public void deleteLine(int lineNumb) {

	}

	@Override
	public void deleteSign(int lineNumb, int signNumb, char deletedSign) {

	}

	@Override
	public void addSign(int lineNumb, int signNumb, String addedSign) {
		System.out.println(text.getText());
		text.setText("<html>" + text.getText().substring(0, signNumb) + addedSign
				+ text.getText().substring(signNumb) + "</html>");
	}

}
