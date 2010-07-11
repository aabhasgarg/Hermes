package hermes.protocol;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class OutProtocol implements ActionsInterface {
    private MultiUserChat muc;

    public OutProtocol(MultiUserChat muc) {
	this.muc = muc;
    }

    @Override
    public void addLine(int line) {
	try {
	    muc.sendMessage("addline@" + line);
	} catch (XMPPException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void deleteSign(int line, int pos, int numb) {
	try {
	    muc.sendMessage("deletesign@" + line);
	} catch (XMPPException e) {
	    e.printStackTrace();
	}
    }

}
