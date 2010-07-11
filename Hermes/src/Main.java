import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class Main {

	private static ChatConnection conn;

	public static void main(String[] args) {
		String username = "braun.markus89@googlemail.com";
		String password = "";
		try {
			conn = new ChatConnection(username, password);
			System.out.println("conn succesful");
		} catch (XMPPException e) {
			System.out.println("conn failed");
			e.printStackTrace();
		}

		if (conn != null) {
			MultiUserChat muc = new MultiUserChat(conn,
					"myroom@talk.google.com");

			try {
				muc.create(username);
				muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
			} catch (XMPPException e) {
				e.printStackTrace();
			}

		}

	}
}
