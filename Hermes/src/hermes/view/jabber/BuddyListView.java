package hermes.view.jabber;

import hermes.view.View;
import hermes.xmpp.ChatConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	this.buddyTable.setFillsViewportHeight(true);
	JScrollPane scrollPane = new JScrollPane(this.buddyTable);
	this.add(scrollPane);

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
		int res = JOptionPane.showConfirmDialog(BuddyListView.this,
			"Diese Aktion kann nicht widerrufen werden",
			"Freund loeschen", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
		    BuddyListView.this.parent.removeBuddy(buddyTable
			    .getSelectedRow());
		    int selected = buddyTable.getSelectedRow();

		}

	    }

	});
    }

    /**
     * Updates the Buddylist. (Don't know if array or list is better here, we
     * will see)
     * 
     * @param bl
     *            the new Buddylist
     */
    public void setBuddyList(RosterEntry[] buddies) {

	this.onlineBuddies = buddies;

	this.repaint();
    }

}
