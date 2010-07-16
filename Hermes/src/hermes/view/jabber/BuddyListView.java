package hermes.view.jabber;

import hermes.Controller;
import hermes.view.View;
import hermes.xmpp.ChatConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

/**
 * Tableview that displays a simple Buddylist
 * 
 * @author Dome
 * 
 */
public class BuddyListView extends JPanel {

    private final JabberView parent;
    private final JTable buddyTable;
    private ChatConnection conn;
    private RosterEntry[] onlineBuddies;

    public BuddyListView(JabberView parent) {
	super();
	this.parent = parent;
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	TableModel dataModel = new AbstractTableModel() {

	    @Override
	    public int getColumnCount() {
		return 1;
	    }

	    @Override
	    public int getRowCount() {
		return onlineBuddies == null ? 0 : onlineBuddies.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
		return onlineBuddies == null ? null : onlineBuddies[rowIndex];
	    }

	    public boolean isCellEditable(int row, int col) {
		return false;
	    }

	};

	this.buddyTable = new JTable(dataModel);
	this.buddyTable.setDefaultRenderer(Object.class,
		new ContactTableRenderer(parent));
	this.buddyTable.setFillsViewportHeight(true);
	JScrollPane scrollPane = new JScrollPane(this.buddyTable);
	this.add(scrollPane);

	this.buddyTable.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getComponent().isEnabled()
			&& e.getButton() == MouseEvent.BUTTON1
			&& e.getClickCount() == 2) {

		    int row = buddyTable.rowAtPoint(e.getPoint());
		    System.out.println("Doubleclick recognized");
		    Controller.CURRENT_INSTANCE.startChatWith(row);

		} else if (e.getButton() == MouseEvent.BUTTON3) {
		    int row = buddyTable.rowAtPoint(e.getPoint());
		    BuddyListView.this.parent.showContextMenu(e, row);
		}
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	});
	JPanel buttons = new JPanel();
	buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
	JButton addContact = new JButton("+");
	buttons.add(addContact);
	JButton removeContact = new JButton("-");
	buttons.add(removeContact);
	this.add(buttons);

	addContact.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		BuddyListView.this.parent.addBuddy();
	    }

	});

	removeContact.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (buddyTable.getSelectedRow() != -1) {
		    int res = JOptionPane.showConfirmDialog(BuddyListView.this,
			    "Diese Aktion kann nicht widerrufen werden",
			    "Freund loeschen", JOptionPane.YES_NO_OPTION);
		    if (res == JOptionPane.YES_OPTION) {
			BuddyListView.this.parent.removeBuddy(buddyTable
				.getSelectedRow());

		    }
		}

	    }

	});
    }

    /**
     * Updates the Buddylist
     * 
     * @param bl
     *            the new Buddylist
     */
    public void setBuddyList(RosterEntry[] buddies) {

	this.onlineBuddies = buddies;
	this.repaint();
    }

}
