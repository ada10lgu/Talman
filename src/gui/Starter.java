package gui;

import gui.controll.Controller;
import gui.viewer.Viewer;

import java.io.File;
import java.io.IOException;

import model.TalmanModel;
import settings.Settings;

public class Starter {
	public static void main(String[] args) throws IOException {

		
		
		String file = "vtmExtra.settings";

		if (args.length > 0)
			file = args[0];

		Settings settings = new Settings(new File(file));

		TalmanModel model = new TalmanModel(settings);
		Controller c = new Controller(model);
		Viewer v = new Viewer(model);
		v.setLocation(200, 200);

		WindowCloser wc = new WindowCloser();

		c.addWindowListener(wc);
		v.addWindowListener(wc);
	}
}