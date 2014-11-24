package model.annex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class AnnexList extends Observable implements Iterable<Annex> {

	private final File ANNEX_FILES = new File("data/annex");

	private ArrayList<Annex> data;
	private Annex active;
	
	
	public AnnexList() throws FileNotFoundException {
		data = new ArrayList<>();
		updateList();
	}

	private void updateList() throws FileNotFoundException {
		data.clear();
		if (!ANNEX_FILES.isDirectory())
			throw new FileNotFoundException("Annexfolder not found!");
		for (File f : ANNEX_FILES.listFiles()) {
			if (f.getName().endsWith(".pdf")) {
				try {
					data.add(new Annex(f,this));
				} catch (IOException e) {
					System.err.println("Could not add " + f);
				}
			}
		}
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
