package model.person;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import org.json.JSONObject;

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
			setChanged();
			notifyObservers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Person> iterator() {
		return persons.iterator();
	}

	public Person search(String stil) {
		for (Person p : persons) {
			if (p.getSTIL().equalsIgnoreCase(stil))
				return p;
		}
		String http = String.format("http://dev-mezz.dsek.se/app/search.php?stil=%s&key=lars", stil);
		try {
			URL ur = new URL(http);
			HttpURLConnection yc = (HttpURLConnection) ur.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine).append("\n");
			in.close();
			JSONObject json = new JSONObject(sb.toString());
			System.out.println(json.toString(4));
			ArrayList<String> temp = new ArrayList<>();
			temp.add(json.getString("stil"));
			temp.add(json.getString("name"));

			String image = json.getString("image");
			if (image.startsWith("/")) {
				image = "https://www.dsek.se" + image;
			}

			temp.add(image);

			return new Person(temp, this);
		} catch (IOException e) {
			System.err.printf("Error searching for user %s, %s%n", stil, e.getMessage());
			return null;
		}
	}

	public void add(Person p) {
		for (Person other : persons) {
			if (p.getSTIL().equalsIgnoreCase(other.getSTIL()))
				return;
		}
		persons.add(p);
		csv.getData().add(p.data);
		save();
	}
}
