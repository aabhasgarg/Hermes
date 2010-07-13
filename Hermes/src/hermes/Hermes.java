package hermes;

import hermes.view.View;

public class Hermes {

    public static void main(String[] args) {
	
	System.setProperty("apple.laf.useScreenMenuBar", "true");
	View v = new View();
	Controller c = new Controller(v);
	v.controller = c;
	
    }

}
