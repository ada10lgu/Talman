package model.agenda;

import model.Heidi;


public class Item implements Comparable<Item> {
	
	private Heidi model;
	private Double number;
	private String name;
	private String type;
	private String annex;
	private boolean accepted;

	public Item(Heidi model, Double number, String name, String type, String annex,
			boolean accepted) {
		this.number = number;
		this.name = name;
		this.type = type;
		this.annex = annex;
		this.accepted = accepted;
		this.model = model;
	}

	@Override
	public String toString() {
		return model.getParagraphSign() + getNumber() + " " + name;
	}

	public String getNumber() {
//		DecimalFormat decimalFormat = new DecimalFormat("#.#");
//		String temp =decimalFormat.format(number);
//		
		String result = "" + number;
		if (result.charAt(result.length()-1) == '0' && result.charAt(result.length()-2)=='.') {
			result = result.substring(0, result.length()-2);
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getAnnex() {
		return annex;
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

	public void update(String number, String name, String type, String annex, boolean accepted) throws NumberFormatException {
		this.number = Double.parseDouble(number);
		this.name = name;
		this.type = type;
		this.annex = annex;
		this.accepted = accepted;
	}

}
