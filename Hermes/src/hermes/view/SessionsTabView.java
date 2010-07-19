package hermes.view;

import hermes.view.jabber.ChatView;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

public class SessionsTabView extends JTabbedPane {

    public SessionsTabView() {
	super();
	this.setTabPlacement(JTabbedPane.TOP);
	this.setPreferredSize(new Dimension(750, 750));
    }

    public void addDocument(DocumentSessionView session, String sessionName) {
	this.addTab(sessionName, session);

    }

    public void removeChat(String sessionName) {
	this.removeTabAt(this.indexOfTab(sessionName));
    }
}
