package hermes.view;

import java.awt.*;
import javax.swing.*;

public class View extends JFrame{
    
    public View() {
	
	this.setLayout(new BorderLayout());
	this.setJMenuBar(new MenuBar());
	
	this.pack();
	this.setMinimumSize(this.getSize());
	this.setVisible(true);
    }
    
}
