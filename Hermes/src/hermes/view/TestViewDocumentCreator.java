package hermes.view;

import hermes.protocol.ActionsInterface;
import hermes.protocol.InProtocol;
import hermes.protocol.OutProtocol;
import hermes.xmpp.ChatConnection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;

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
		setSize(800, 400);
		initConnection();
		if (conn == null)
			System.exit(0);
		initMultiuserChat();
		initProtocols();
		addMultiuserListener();
		for(RosterEntry ent:conn.getRoster().getEntries()){
			System.out.println(ent.getName());
		}
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
		String roomName = "bbblablubbernewroom@conference.jabber.org";
		muc = new MultiUserChat(conn, roomName);

		System.out.println("check existing rooms");
		Map<String, Boolean> entries = createBlockedRoomsMap();

		if (entries.containsKey("bbblablubbernewroom@conference.jabber.org")) {
			System.out.println("room already exists");
			try {
				muc.join(username);
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("room doesn't exist");
			try {
				muc.create(username);
				muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
		System.out.println("existing rooms checked");

		
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

	private Map<String, Boolean> createBlockedRoomsMap() {
		// Assume we have a valid connection
		// List all the services
		Map<String, Boolean> entries = new HashMap<String, Boolean>();
		try {
			for (String service : MultiUserChat.getServiceNames(conn)) {
				// System.out.println("Service name: " + service);
				// Get the list of rooms under this service
				for (HostedRoom room : MultiUserChat.getHostedRooms(conn,
						service)) {
					entries.put(room.getJid(), true);
					// System.out.println("\tName: " + room.getName());
					// System.out.println("\tRoom JID: " + room.getJid());
					// if (room.getJid().contains("bbblablubbernewroom")) {
					// System.out.println("room exists");
					// break;
					// }

					// Get the detail information on the room
					/*
					 * RoomInfo info = MultiUserChat.getRoomInfo(conn,
					 * room.getJid()); if(info!=null){
					 * System.out.println("\tDescription: " +
					 * info.getDescription()); System.out.println("\tOccupant: "
					 * + info.getOccupantsCount());
					 * System.out.println("\tPassword: " +
					 * info.isPasswordProtected()); }
					 */
				}
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		return entries;
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
		text.setText(text.getText().substring(0, signNumb) + addedSign
				+ text.getText().substring(signNumb));
	}

}
