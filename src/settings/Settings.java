package settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import standalone.csv.CSV;

public class Settings {
	private CSV csv;
	private HashMap<String, ArrayList<String>> data;
	private File folder;

	private static final String[][] PRESETS = new String[][] { { "title", "Möte" }, { "paragraph", "§" },
			{ "agenda", "data/agenda" }, { "sang", "rosa" } };

	public Settings(File folder) throws IOException {
		this.folder = folder;
		if (!folder.isDirectory()) {
			throw new RuntimeException("Selected folder is not a folder.");
		}
		File f = new File(folder, "settings");
		System.out.printf("Settings file: %s%n", f);
		csv = new CSV(f);
		data = new HashMap<>();

		for (ArrayList<String> row : csv.getData()) {
			if (row.size() < 2)
				throw new IOException("Illegal row information");
			data.put(row.get(0), row);
		}

		enterPresets();

		csv.save();
	}

	private void enterPresets() {
		for (String[] pre : PRESETS) {
			String key = pre[0];
			String value = pre[1];
			ArrayList<String> row = new ArrayList<>();
			row.add(key);
			row.add(value);
			if (data.get(key) == null) {
				data.put(key, row);
				csv.getData().add(row);
				System.err.println("Found no entry for " + key + ". Entered standard \"" + value + "\"");

			}
		}
	}

	public synchronized String getTitle() {
		return get("title");
	}

	public synchronized void updateTitle(String value) {
		update("title", value);
	}

	public synchronized String getParagraph() {
		return get("paragraph");
	}

	public synchronized String getAgenda() {
		return get("agenda");
	}

	public synchronized File getSang() {
		return new File(get("sang"));
	}

	private String get(String title) {
		return data.get(title).get(1);
	}

	private void update(String title, String value) {
		data.get(title).remove(1);
		data.get(title).add(1, value);
		try {
			csv.save();
		} catch (IOException e) {
			System.err.println("Changes not saved to file");
		}
	}

	public File getFolder() {
		return folder;
	}

}
