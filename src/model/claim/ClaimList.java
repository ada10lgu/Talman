package model.claim;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import settings.Settings;
import standalone.csv.CSV;

public class ClaimList extends Observable implements Iterable<Claim> {

	private File file;
	private ArrayList<Claim> claims;
	private Claim active;
	private CSV csv;

	public ClaimList(Settings settings) {
		file = new File(settings.getFolder(), "claims");
		claims = new ArrayList<>();
		loadClaimes();
	}

	private void loadClaimes() {
		csv = null;
		try {
			csv = new CSV(file);
		} catch (IOException e) {
			System.err.println("Could not load claims file");
			System.exit(1);
		}

		for (ArrayList<String> claim : csv.getData())
			claims.add(new Claim(claim, this));
	}

	public synchronized void addClaim(String name, String author, String description) {
		ArrayList<String> data = new ArrayList<>();
		description = description.replaceAll("\n", "<br>");
		data.add(name);
		data.add(author);
		data.add(description);
		claims.add(new Claim(data, this));
		csv.getData().add(data);
		setChanged();
		notifyObservers();
	}

	public synchronized void removeClaim(Claim c) {
		claims.remove(c);
		csv.getData().remove(c.getData());
		if (active == c)
			active = null;
		setChanged();
		notifyObservers();
	}

	public synchronized void save() {
		try {
			csv.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Claim> iterator() {
		return claims.iterator();
	}

	public synchronized void setActive(Claim c) {
		active = c;
		setChanged();
		notifyObservers();
	}

	public synchronized Claim getActiveClaim() {
		return active;
	}

	public boolean exists(Claim c) {
		return claims.indexOf(c) != -1;
	}

	void update() {
		setChanged();
		notifyObservers();
	}

}
