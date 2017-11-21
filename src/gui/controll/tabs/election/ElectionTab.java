package gui.controll.tabs.election;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.person.PersonList;

@SuppressWarnings("serial")
public class ElectionTab extends JPanel {

	public ElectionTab(PersonList pl) {
		setLayout(new GridLayout(1, 2));

		ElectionViewer pw = new ElectionViewer();

		JPanel left = new ListPanel(pl, pw);
		JPanel right = new JPanel();

		right.setLayout(new GridLayout(2, 1));
		right.add(pw);
		right.add(new CreateElection(pl));
		add(left);
		add(right);

	}
}
