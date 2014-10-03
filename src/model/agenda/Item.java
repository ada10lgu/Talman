package model.agenda;

import java.text.DecimalFormat;

public class Item implements Comparable<Item> {
	private Double number;
	private String name;
	private boolean accepted;

	public Item(Double number, String name, boolean accepted) {
		this.number = number;
		this.name = name;
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "§" + getNumber() + "\t" + name;
	}

	public String getNumber() {
		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		return decimalFormat.format(number);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
	@Override
	public int compareTo(Item i) {
		return number.compareTo(i.number);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item)
			return ((Item) obj).compareTo(this) == 0;
		return false;
	}
}
