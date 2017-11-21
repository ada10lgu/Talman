package model.person;

import java.io.File;
import java.io.IOException;

import settings.Settings;
import standalone.csv.CSV;

public class PersonList {

	private File file;
	private CSV csv;

	public PersonList(Settings settings) {
		file = new File(settings.getFolder(), "people");

		try {
			csv = new CSV(file);
		} catch (IOException e) {
			System.err.println("Could not load people list");
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
