package model.annex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import settings.Settings;

public class AnnexList extends Observable implements Iterable<Annex> {

	private File folder;
	private ArrayList<Annex> data;
	private Annex active;

	public AnnexList(Settings settings) throws FileNotFoundException {
		data = new ArrayList<>();

		folder = new File(settings.getFolder(), "annex");

		if (!folder.exists())
			folder.mkdir();

		updateList();
	}

	private void updateList() throws FileNotFoundException {
		data.clear();
		if (!folder.isDirectory())
			throw new FileNotFoundException("Annexfolder not found!");
		for (File f : folder.listFiles()) {
			if (f.getName().endsWith(".pdf")) {
				try {
					data.add(new Annex(f, this));
				} catch (IOException e) {
					System.err.println("Could not add " + f);
				}
			}
		}
		Collections.sort(data);
	}

	@Override
	public Iterator<Annex> iterator() {
		return data.iterator();

	}

	public synchronized void setActive(Annex a) {
		active = a;
		setChanged();
		notifyObservers();
	}

	public synchronized Annex getActive() {
		return active;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}

}
