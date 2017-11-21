package model.election;

import java.io.File;
import java.io.IOException;

import settings.Settings;
import standalone.csv.CSV;

public class ElectionList {

	private File file;
	private CSV csv;

	public ElectionList(Settings settings) {
		file = new File(settings.getFolder(), "election");

		try {
			csv = new CSV(file);
		} catch (IOException e) {
			System.err.println("Could not load election list");
			System.exit(1);
		}
	}

	public void save() {
		try {
			csv.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
