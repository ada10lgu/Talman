package gui.controll.tabs.election;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.person.Person;
import model.person.PersonList;

@SuppressWarnings("serial")
public class CreateElection extends JPanel {

	private Person active;

	public CreateElection(final PersonList pl) {
		setLayout(new GridLayout(1, 2));

		JPanel left = new JPanel();

		final ElectionViewer pw = new ElectionViewer();

		final JTextField stil = new JTextField(10);
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				active = pl.search(stil.getText());
				pw.setActive(active);
			}
		});
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (active != null) {
					pl.add(active);
				}
			}
		});
		left.add(stil);
		left.add(search);
		left.add(add);

		add(left);
		add(pw);

	}
}
