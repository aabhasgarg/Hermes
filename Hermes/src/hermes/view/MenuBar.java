package hermes.view;

import javax.swing.*;


public class MenuBar extends JMenuBar {
    
    private JMenu File;
    
    public MenuBar() {
	super();
	File = new JMenu("File");
	File.add(new JMenuItem("New"));
	this.add(File);
	
    }
    
}
