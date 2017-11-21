package model.election;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import model.person.PersonList;
import settings.Settings;

public class ElectionList extends Observable implements Iterable<Election>{

	private File file;
	private JSONArray json;
	private ArrayList<Election> elections;
	private PersonList pl;

	public ElectionList(Settings settings, PersonList pl) {
		this.pl = pl;

		file = new File(settings.getFolder(), "election");

		try {
			if (!file.exists()) {
				json = new JSONArray();
				save();
			} else {
				Scanner s = new Scanner(file);
				StringBuilder sb = new StringBuilder();
				while (s.hasNextLine()) {
					sb.append(s.nextLine()).append("\n");
				}
				s.close();
				json = new JSONArray(sb.toString());
			}
		} catch (IOException e) {
			System.err.println("Could not load election list");
			System.exit(1);
		}

		elections = new ArrayList<>();

		for (int i = 0; i < json.length(); i++) {
			elections.add(new Election(json.getJSONObject(i), this, pl));
		}
		
	}

	public void save() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(json.toString(4));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void create(String title) {
		for (Election e : elections) {
			if (e.getTitle().equalsIgnoreCase(title)) {
				return;
			}
		}
		JSONObject electionJson = new JSONObject();
		electionJson.put("title", title);
		Election e = new Election(electionJson, this, pl);
		json.put(electionJson);
		elections.add(e);
		save();
	}

	@Override
	public Iterator<Election> iterator() {
		return elections.iterator();
	}
}
