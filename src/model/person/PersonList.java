package model.person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import settings.Settings;
import standalone.csv.CSV;

public class PersonList extends Observable implements Iterable<Person> {

	private File file;
	private CSV csv;

	private ArrayList<Person> persons;

	public PersonList(Settings settings) {
		file = new File(settings.getFolder(), "people");
		persons = new ArrayList<>();

		loadPersons();
	}

	private void loadPersons() {
		csv = null;
		try {
			csv = new CSV(file);
		} catch (IOException e) {
			System.err.println("Could not load peoples file");
			System.exit(1);
		}

		for (ArrayList<String> person : csv.getData())
			persons.add(new Person(person, this));
	}

	public void save() {
		try {
			csv.save();
			notifyObservers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Person> iterator() {
		return persons.iterator();
	}
}
