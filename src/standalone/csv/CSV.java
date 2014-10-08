package standalone.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

	private File f;
	private ArrayList<ArrayList<String>> data;

	private final static char COLUMN_SEPERATOR = ',';
	private final static char STRING_CONTAINER = '"';

	public CSV(File f) throws FileNotFoundException {
		this.f = f;

		data = new ArrayList<>();

		Scanner s = new Scanner(f);

		while (s.hasNext()) {
			data.add(parseRow(s.nextLine()));
		}

		s.close();
	}

	private ArrayList<String> parseRow(String row) {
		ArrayList<String> data = new ArrayList<>();

		String s = "";
		char expect = STRING_CONTAINER;
		for (int i = 0; i < row.length(); i++) {
			char c = row.charAt(i);
			switch (expect) {
			case STRING_CONTAINER:
				if (c != STRING_CONTAINER)
					throw new RuntimeException("Illegal character");
				s = "";
				expect = (char) -1;
				break;
			case COLUMN_SEPERATOR:
				data.add(s);
				expect = STRING_CONTAINER;
				break;

			default:
				if (c != STRING_CONTAINER)
					s += c;
				else
					expect = COLUMN_SEPERATOR;
				break;
			}
		}
		data.add(s);
		return data;
	}

	public void save() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(f);

		for (ArrayList<String> row : data) {
			if (row.isEmpty())
				continue;
			pw.print(STRING_CONTAINER);
			pw.print(row.get(0));
			pw.print(STRING_CONTAINER);
			for (int i = 1; i < row.size(); i++) {
				pw.print(COLUMN_SEPERATOR);
				pw.print(STRING_CONTAINER);
				pw.print(row.get(i));
				pw.print(STRING_CONTAINER);
			}
			pw.println();
		}

		pw.flush();
		pw.close();
	}

	public ArrayList<ArrayList<String>> getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
