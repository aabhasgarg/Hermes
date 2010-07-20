package hermes.view;

import hermes.Controller;
import hermes.datastructures.DocSession;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MucListCellRenderer implements ListCellRenderer {

    private static DefaultListCellRenderer defRenderer = new DefaultListCellRenderer();
    private final DocSession session;

    public MucListCellRenderer(DocSession s) {
	this.session = s;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
	    int index, boolean isSelected, boolean cellHasFocus) {
	JLabel l = (JLabel) defRenderer.getListCellRendererComponent(list,
		value, index, false, false);
	String from = session.getMucMessage(index).getFrom().split("/")[1];
	if (from.equals(Controller.CURRENT_INSTANCE.getThisUser())) {
	    l.setAlignmentX(JLabel.LEFT_ALIGNMENT);
	} else {
	    l.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
	}
	return l;
    }

}
