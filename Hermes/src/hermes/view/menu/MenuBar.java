package hermes.view.menu;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private JMenu file = new JMenu("File");;
    private JMenu edit = new JMenu("Edit");

    public MenuBar() {
	super();
	initFile();
	initEdit();

    }

    private void initFile() {
	this.add(file);
	file.add(new JMenuItem("New"));
    }

    private void initEdit() {
	this.add(edit);
    }
}
