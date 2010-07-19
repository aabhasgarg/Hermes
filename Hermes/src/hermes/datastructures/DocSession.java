package hermes.datastructures;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.StyledDocument;

import org.jivesoftware.smackx.muc.MultiUserChat;

import hermes.view.DocumentSessionView;

public class DocSession {

    public final MultiUserChat muc;

    public String localName;
    public DocumentSessionView sessionView;
    public List<String> members;
    public String masterMember;
    public StyledDocument document;

    public DocSession(MultiUserChat muc) {
	this.muc = muc;
	members = new LinkedList<String>();
    }
}
