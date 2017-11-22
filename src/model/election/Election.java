package model.election;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.person.Person;
import model.person.PersonList;

public class Election {

	private JSONObject json;
	private PersonList pl;
	private ElectionList el;

	public Election(JSONObject json, ElectionList el, PersonList pl) {
		this.json = json;
		this.pl = pl;
		this.el = el;
	}

	public String getTitle() {
		return json.getString("title");
	}

	public void setParticipants(String text) {
		JSONArray participants = new JSONArray();

		for (String s : text.split(",")) {
			Person p = pl.getOrCreateByStil(s.trim());
			if (p != null) {
				participants.put(p.getSTIL());
			}
		}

		json.put("participants", participants);
		el.save();
	}

	public List<Person> getParticipants() {
		ArrayList<Person> ppl = new ArrayList<>();
		if (!json.has("participants"))
			return ppl;
		for (Object o : json.getJSONArray("participants")) {
			ppl.add(pl.search(o.toString()));
		}

		return ppl;
	}

	@Override
	public String toString() {
		List<Person> ppl = getParticipants();
		if (ppl.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(ppl.get(0).getSTIL());
		for (int i = 1; i < ppl.size(); i++) {
			sb.append(",").append(ppl.get(i).getSTIL());
		}
		return sb.toString();
	}
}
