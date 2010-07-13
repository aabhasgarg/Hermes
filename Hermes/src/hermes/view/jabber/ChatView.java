package hermes.view.jabber;

import javax.swing.JTabbedPane;

public class ChatView extends JTabbedPane {

    private final JabberView parent;

    public ChatView(JabberView jv) {
	this.parent = jv;
    }
}
