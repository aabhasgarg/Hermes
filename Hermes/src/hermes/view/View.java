package hermes.view;

import hermes.datastructures.Buddy;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

public class View extends JFrame {

    private final BuddyListView buddyListView;

    public View() {

	this.setLayout(new BorderLayout());
	this.buddyListView = new BuddyListView(this);
	this.add(buddyListView, BorderLayout.WEST);

	this.pack();
	this.setMinimumSize(this.getSize());
	this.setVisible(true);
    }

    public void setBuddyList(List<Buddy> bl) {
	this.buddyListView.setBuddyList(bl);
    }

}
