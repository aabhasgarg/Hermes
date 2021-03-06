package hermes.view.document;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

/**
 * this class provides the interface to put the MU sessions in multiple tabs and
 * switch between them
 * 
 * @author Dome
 * 
 */
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

    // TODO little close button to all tabs to exit a session
}
