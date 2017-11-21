package gui.controll.tabs.election;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.election.ElectionList;

@SuppressWarnings("serial")
public class ElectionTab extends JPanel {

	public ElectionTab(ElectionList el) {
		setLayout(new GridLayout(1, 2));

		ElectionViewer ew = new ElectionViewer();

		JPanel left = new ListPanel(el, ew);
		JPanel right = new JPanel();

		right.setLayout(new GridLayout(2, 1));
		right.add(ew);
		right.add(new CreateElection(el));
		add(left);
		add(right);

	}
}
