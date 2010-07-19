package hermes.view;

import hermes.datastructures.DocSession;

import javax.swing.BoxLayout;
import javax.swing.JTextPane;

import javax.swing.JPanel;

public class DocumentSessionView extends JPanel {

    private final DocSession session;

    private final JTextPane textView;

    public DocumentSessionView(DocSession session) {
	this.session = session;
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	textView = new JTextPane();
	this.add(textView);
	
	
    }

    public String getLocalName() {
	return session.localName;
    }
}
