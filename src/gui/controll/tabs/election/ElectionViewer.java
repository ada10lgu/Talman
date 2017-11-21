package gui.controll.tabs.election;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.election.Election;
import model.election.ElectionList;

@SuppressWarnings("serial")
public class ElectionViewer extends JPanel {

	private Election active;
	private JLabel title;
	private JTextField participants;

	public ElectionViewer(ElectionList el) {
		setBackground(Color.WHITE);

		JButton display = new JButton("Display");
		display.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				el.setDisplay(active);
			}
		});
		participants = new JTextField(40);
		participants.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				active.setParticipants(participants.getText());
			}
		});
		title = new JLabel("None selected");

		add(title);
		add(display);
		add(participants);
	}

	public void setActive(Election e) {
		active = e;
		if (e != null) {
			title.setText(e.getTitle());
			participants.setText(e.toString());
		}
		repaint();
	}

}
