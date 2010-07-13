package hermes.view.jabber;

import javax.swing.JTabbedPane;

public class ChatView extends JTabbedPane {

    private final JabberView parent;

    public ChatView(JabberView jv) {
	super();
	this.parent = jv;
	this.setTabPlacement(JTabbedPane.TOP);
    }

    public void addChat(ChatView chatView, String username) {
	this.addTab(username, chatView);
    }

    public void removeChat(String username) {
	this.removeTabAt(this.indexOfTab(username));
    }
}
