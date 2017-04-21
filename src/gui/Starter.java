package gui;

import gui.controll.Controller;
import gui.viewer.Viewer;

import java.io.File;
import java.io.IOException;

import model.Heidi;
import settings.Settings;

public class Starter {
	public static void main(String[] args) throws IOException {

		String file = "";

		if (args.length > 0)
			file = args[0];

		Settings settings = new Settings(new File(file));

		Heidi model = new Heidi(settings);
		Controller c = new Controller(model);
		Viewer v = new Viewer(model);
		v.setLocation(200, 200);

		WindowCloser wc = new WindowCloser();

		c.addWindowListener(wc);
		v.addWindowListener(wc);
	}
}