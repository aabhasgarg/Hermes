package hermes.view;

import hermes.datastructures.Buddy;

import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Tableview that displays a simple Buddylist
 * 
 * @author Dome
 * 
 */
public class BuddyListView extends JFrame {

    private final View parent;
    private final JTable buddyTable;
    private List<Buddy> onlineBuddies;

    public BuddyListView(View parent) {
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
		return onlineBuddies == null ? 0 : onlineBuddies.size();
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
		return onlineBuddies == null ? null : onlineBuddies
			.get(rowIndex);
	    }

	    public boolean isCellEditable(int row, int col) {
		return false;
	    }

	};

	this.buddyTable = new JTable(dataModel);
	this.buddyTable.setFillsViewportHeight(true);

	JScrollPane scrollPane = new JScrollPane(this.buddyTable);
	this.add(scrollPane);

    }

    /**
     * Updates the Buddylist. (Don't know if array or list is better here, we
     * will see)
     * 
     * @param bl
     *            the new Buddylist
     */
    public void newBuddyList(List<Buddy> bl) {
	this.onlineBuddies = bl;

    }
}
