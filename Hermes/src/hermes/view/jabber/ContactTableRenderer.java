package hermes.view.jabber;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.smack.packet.Presence;

public class ContactTableRenderer implements TableCellRenderer {
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
    public static Font boldFont = new Font(
	    DEFAULT_RENDERER.getFont().getName(), Font.BOLD, DEFAULT_RENDERER
		    .getFont().getSize());
    public final JabberView jabberView;

    public ContactTableRenderer(JabberView jabberView) {
	this.jabberView = jabberView;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {
	Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
		table, value, isSelected, hasFocus, row, column);
	Color foreground, background;

	Presence pr = jabberView.getJabberStatus(row);
	background = Color.WHITE;
	if (pr.isAvailable()) {

	    foreground = new Color(0, 205, 0);

	} else if (pr.isAway()) {

	    foreground = Color.RED;

	} else {

	    foreground = Color.GRAY;

	}
	renderer.setForeground(foreground);
	renderer.setBackground(background);
	if (isSelected) {

	    renderer.setFont(boldFont);
	}
	return renderer;
    }
}
