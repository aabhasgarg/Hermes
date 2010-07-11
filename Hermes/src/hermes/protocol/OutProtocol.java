package hermes.protocol;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class OutProtocol implements ActionsInterface {

	private MultiUserChat muc;
	private String username;

	public OutProtocol(MultiUserChat muc, String username) {
		this.muc = muc;
		this.username = username;
	}

	@Override
	public void addLine(int lineNumb) {
	}

	@Override
	public void addSign(int lineNumb, int signNumb, String addedSign) {
		System.out.println("sending sign added");
		try {
			muc.sendMessage("fromuser=" + username + "&command=addSign" + "&lineNumb=" + lineNumb + "&signNumb="
					+ signNumb + "&addedSign=" + addedSign);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLine(int lineNumb) {
	}

	@Override
	public void deleteSign(int lineNumb, int signNumb, char deletedSign) {
	}
}
