package model.election;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import model.person.PersonList;

public class Election {

	private JSONObject json;

	public Election(JSONObject json, ElectionList el, PersonList pl) {
		this.json = json;
	}

	public List<Elector> getElectors() {
		return new ArrayList<>();
	}

	public String getTitle() {
		return json.getString("title");
	}
}
