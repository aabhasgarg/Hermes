import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class ChatConnection extends XMPPConnection {

	public ChatConnection(String username, String password)
			throws XMPPException {
		super(new ConnectionConfiguration("talk.google.com", 5222, "gmail.com"));

		connect();

		SASLAuthentication.supportSASLMechanism("PLAIN", 0);

		login(username, password);

		// See if you are authenticated

		System.out.println(isAuthenticated());

	}
}
