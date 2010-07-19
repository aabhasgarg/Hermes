package hermes.view.jabber;

import hermes.Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu {

    private static ContextMenu instance;
    private static int user;

    private ContextMenu() {
	JMenuItem startSession = new JMenuItem("Start editing session");
	this.add(startSession);
	startSession.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		Controller.CURRENT_INSTANCE.startDocSessionWith(user);
	    }
	});
    }

    private static void checkInstance() {
	if (instance == null) {
	    instance = new ContextMenu();
	}

    }

    public static void show(Component invoker, MouseEvent e, int userNumber) {
	checkInstance();
	user = userNumber;
	instance.show(invoker, e.getX(), e.getY());
    }
}
