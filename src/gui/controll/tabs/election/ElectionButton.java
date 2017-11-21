package gui.controll.tabs.election;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.election.Election;

@SuppressWarnings("serial")
public class ElectionButton extends JButton implements ActionListener {

	private Election e;
	private ElectionViewer ew;

	public ElectionButton(Election e, ElectionViewer ew) {
		super(e.getTitle());
		this.e = e;
		this.ew = ew;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ew.setActive(e);
	}

}
