package gui;

import model.Heidi;
import gui.controll.Controller;
import gui.viewer.Viewer;

public class Starter {
	public static void main(String[] args) {
		Heidi model = new Heidi();
		Controller c = new Controller(model);
		Viewer v = new Viewer(model);
		v.setLocation(200, 200);
		
		WindowCloser wc = new WindowCloser();
		
		
		c.addWindowListener(wc);
		v.addWindowListener(wc);
		
		
	}
}
