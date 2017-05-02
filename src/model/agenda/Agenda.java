package model.agenda;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.TalmanModel;

public class Agenda extends Observable implements Iterable<Item> {

	private final String DATA_SEPERATOR = "<!>";
	private final String FOLDER = "data";
	private final String FILE = "agenda";
	private ArrayList<Item> items = new ArrayList<>();
	private Item active;
	private TalmanModel model;

	public Agenda(TalmanModel model) {
		this.model = model;
		loadFile();
	}

	private void loadFile() {
		File folder = new File(FOLDER);
		if (!folder.exists())
			folder.mkdir();
		File f = new File(folder, FILE);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (IOException e) {
			System.err.println("Could not open file " + f.toString());
			System.exit(1);
		}

		while (s.hasNext()) {
			String row = s.nextLine();
			String[] data = row.split(DATA_SEPERATOR);

			if (data.length != 5) {
				System.out.println(data.length);
				System.out.println(row);
				break;
			}
			Double number = Double.parseDouble(data[0]);
			boolean b = data[4].trim().equals("true");

			Item i = new Item(model, number, data[1].trim(), data[2].trim(),
					data[3].trim(), b);
			items.add(i);
		}
		Collections.sort(items);
		setChanged();
		notifyObservers();
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public boolean isActive(Item i) {
		return i == active;
	}

	public void setActive(Item i) {
		active = i;
		setChanged();
		notifyObservers();
	}

	public Item getActive() {
		return active;
	}

	public void updateActive(String number, String name, String type,
			String annex, boolean accepted) throws NumberFormatException {
		active.update(number, name, type, annex, accepted);

		Collections.sort(items);
		setChanged();
		notifyObservers();

	}

	public void newItem(String number, String name, String type, String annex) {
		double n = Double.parseDouble(number);

		Item i = new Item(model, n, name, type, annex, false);
		items.add(i);
		Collections.sort(items);
		setChanged();
		notifyObservers();

	}

	public void saveAll() {
		File f = new File(new File(FOLDER), FILE);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (IOException e) {
			System.err.println("Error accessing file to save " + f.toString());
			JOptionPane.showMessageDialog(null, "Could not save to file! \n"
					+ e.getMessage());
		}

		for (Item i : items) {
			String accepted = i.isAccepted() ? "true" : "false";
			pw.print(i.getNumber());
			pw.print(DATA_SEPERATOR);
			pw.print(i.getName());
			pw.print(DATA_SEPERATOR);
			pw.print(i.getType());
			pw.print(DATA_SEPERATOR);
			pw.print(i.getAnnex());
			pw.print(DATA_SEPERATOR);
			pw.print(accepted);
			pw.println();
		}

		pw.flush();
		pw.close();

	}

	public void removeActive() {
		if (active.isAccepted())
			return;
		items.remove(active);
		active = null;
		setChanged();
		notifyObservers();

	}

}
