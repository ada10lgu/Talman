package gui.controll.tabs.person;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.person.PersonList;

@SuppressWarnings("serial")
public class PersonTab extends JPanel {

	public PersonTab(PersonList pl) {
		setLayout(new GridLayout(1, 2));

		PersonViewer pw = new PersonViewer();

		JPanel left = new ListPanel(pl, pw);
		JPanel right = new JPanel();

		right.setLayout(new GridLayout(2, 1));
		right.add(pw);
		add(left);
		add(right);

	}
}
