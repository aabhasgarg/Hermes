import hermes.view.View;
import hermes.xmpp.ChatConnection;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class Main {

	private static ChatConnection conn;

	public static void main(String[] args) {
		String username = "YoFrankie";
		String password = "testit";
		try {
			conn = new ChatConnection(username, password);
			System.out.println("conn succesful");
		} catch (XMPPException e) {
			System.out.println("conn failed");
			e.printStackTrace();
		}

		if (conn != null) {
			final MultiUserChat muc = new MultiUserChat(conn,
					"myblub@conference.jabber.org");
			try {
				muc.create("random");
				muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
				muc.addMessageListener(new PacketListener() {
					
					@Override
					public void processPacket(Packet pack) {
						System.out.println("received pack");
						System.out.println(muc.nextMessage().getBody());
					}
				});
				muc.sendMessage("hello multiuser chat");
				System.out.println("message sent");
				
			} catch (XMPPException e) {
				e.printStackTrace();
			}

		}
		while(true) {

		}
	}
}
