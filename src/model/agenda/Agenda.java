package model.agenda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Agenda implements Iterable<Item> {
	private ArrayList<Item> items = new ArrayList<>();
	
	
	public Agenda() {
		loadFile();
	}


	private void loadFile() {
		File f = new File("data/agenda");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (IOException e) {
			System.err.println("Could not open file " + f.toString());
			System.exit(1);
		}
		
		while (s.hasNext()) {
			String row = s.nextLine();
			String[] data = row.split("\\s+",2);
			Double number = Double.parseDouble(data[0]);
			Item i = new Item(number,data[1],true);
			items.add(i);
		}
		Collections.sort(items);
	}


	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}
	
}
