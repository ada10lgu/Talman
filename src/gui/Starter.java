package gui;

import java.io.File;
import java.io.IOException;

import gui.controll.Controller;
import gui.viewer.Viewer;
import model.TalmanModel;
import settings.Settings;

public class Starter {

	public static final File DATA_FOLDER = new File("data");

	public static void main(String[] args) throws IOException {
		File folder = DATA_FOLDER;
		if (args.length > 0)
			folder = new File(args[0]);

		if (!folder.exists()) {
			if (!folder.mkdir() || !folder.isDirectory()) {
				System.err.println("Error creating data folder (" + folder.getAbsolutePath() + ").");
			}
		}

		Settings settings = new Settings(folder);
		TalmanModel model = new TalmanModel(settings);
		Controller c = new Controller(model);
		Viewer v = new Viewer(model);
		v.setLocation(200, 200);

		WindowCloser wc = new WindowCloser();

		c.addWindowListener(wc);
		v.addWindowListener(wc);
	}
}