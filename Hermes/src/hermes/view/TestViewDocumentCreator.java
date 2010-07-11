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

public class TestViewDocumentCreator extends JFrame implements ActionsInterface {

	private ChatConnection conn;
	private OutProtocol outProtocol;
	private InProtocol inProtocol;
	private String username = "YoFrankie";
	private String password = "testit";
	private MultiUserChat muc;
	private JTextArea text;
	
	public TestViewDocumentCreator() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		setSize(200, 200);
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
				System.out.println("received package");
				inProtocol.parse((Message) pack);
			}
		});
	}
	
	private void initMultiuserChat() {
		muc = new MultiUserChat(conn,
				"blubbernewroom@conference.jabber.org");
		try {
			muc.create(username);
			muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//			muc.join(username);

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter pressed");
				} else {
					outProtocol.addSign(0, 0, String.valueOf(e.getKeyChar()));
				}
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
		new TestViewDocumentCreator().setVisible(true);
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
		text.setText(text.getText() + addedSign);
	}

}
