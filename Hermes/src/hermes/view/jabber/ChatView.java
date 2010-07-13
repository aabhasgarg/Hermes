package hermes.view.jabber;

import javax.swing.JPanel;

public class ChatView extends JPanel {

    private final JabberView parent;

    public ChatView(JabberView jv) {
	this.parent = jv;
    }
}
