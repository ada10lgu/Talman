package gui.controll.tabs.election;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.person.Person;

@SuppressWarnings("serial")
public class ElectionButton extends JButton implements ActionListener {

	private Person p;
	private ElectionViewer pw;

	public ElectionButton(Person p, ElectionViewer pw) {
		super(p.getName());
		this.p = p;
		this.pw = pw;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		pw.setActive(p);
	}

}
