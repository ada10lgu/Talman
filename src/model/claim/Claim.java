package model.claim;

import java.util.ArrayList;

public class Claim {

	private ArrayList<String> data;
	private ClaimList cl;

	public Claim(ArrayList<String> data, ClaimList cl) {
		this.data = data;
		this.cl = cl;
	}

	public String getDesctiption() {
		return data.get(2).replaceAll("<br>", "\n");
	}

	public String getName() {
		return data.get(0);
	}

	public String getAuthor() {
		return data.get(1);
	}
	
	ArrayList<String> getData() {
		return data;
	}

	public void update(String name, String author, String description) {
		data.clear();
		data.add(name);
		data.add(author);
		data.add(description.replaceAll("\n", "<br>"));
		cl.update();
	}

}
