package hermes;

import hermes.datastructures.DocSession;
import hermes.view.DocumentSessionView;
import hermes.view.View;

import java.util.HashMap;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class DocumentAdministration {

    private final Controller controller;
    private HashMap<String, DocSession> currentMucs;

    public DocumentAdministration(Controller controller) {
	this.controller = controller;
	this.currentMucs = new HashMap<String, DocSession>();
    }

    public void initDocEditing(String with) {

	DocSession session = createMuc();
	session.localName = "New File";
	session.sessionView = new DocumentSessionView(session);
	View.CURRENT_INSTANCE.addDocument(session.sessionView);

    }

    public void editExistingFileWith(String user) {

    }

    public void addUserToSession(DocSession session, String user) {

    }

    public DocSession createMuc() {
	MultiUserChat muc;
	do {

	    String mucName = "hermes." + controller.conn.getUser()
		    + (System.currentTimeMillis() / 1000);
	    muc = new MultiUserChat(controller.conn, mucName);

	    try {
		muc.create(controller.getThisUser());
		muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
	    } catch (XMPPException e) {
		e.printStackTrace();
		continue;
	    }
	} while (!muc.isJoined());

	DocSession s = new DocSession(muc);
	s.masterMember = controller.getThisUser();
	s.members.add(s.masterMember);
	return s;
    }
}
