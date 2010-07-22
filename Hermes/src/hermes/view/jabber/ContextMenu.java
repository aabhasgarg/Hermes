package hermes.view.jabber;

import hermes.Controller;
import hermes.DocumentAdministration;
import hermes.datastructures.DocSession;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.packet.Presence;

public class ContextMenu extends JPopupMenu {

    private static ContextMenu instance;
    private static int userClicked;

    private JMenuItem inviteTo;
    private HashMap<Object, DocSession> sessions;

    private ContextMenu() {
	this.sessions = new HashMap<Object, DocSession>();
	JMenuItem startSession = new JMenuItem("Start new session");
	this.add(startSession);
	startSession.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		Controller.CURRENT_INSTANCE.startDocSessionWith(userClicked);
	    }
	});

	JMenuItem editExisting = new JMenuItem("Edit existing File");
	this.add(editExisting);
	editExisting.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		Controller.CURRENT_INSTANCE.editExistingFileWith(userClicked);
	    }

	});
	inviteTo = new JMenu("Invite User to Session");
	this.add(inviteTo);

    }

    private void updateSessions() {
	inviteTo.removeAll();
	sessions.clear();
	for (DocSession s : DocumentAdministration.CURRENT_INSTANCE
		.getOpenSessions().values()) {
	    if (!s
		    .isUserInSession(Controller.CURRENT_INSTANCE.buddyList[userClicked]
			    .getUser())) {

		JMenuItem user = new JMenuItem(s.localName);
		inviteTo.add(user);
		sessions.put(user, s);
		user.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
			sessions.get(e.getSource()).inviteUser(userClicked);

		    }

		});

	    }
	}

    }

    private static void checkInstance() {
	if (instance == null) {
	    instance = new ContextMenu();
	}
	instance.updateSessions();
    }

    public static void show(Component invoker, MouseEvent e, int userNumber) {
	checkInstance();
	userClicked = userNumber;
	instance.setLabel(Controller.CURRENT_INSTANCE.buddyList[userNumber]
		.getUser());
	instance.show(invoker, e.getX(), e.getY());
    }
}
