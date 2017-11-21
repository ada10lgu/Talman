package gui.controll.tabs.person;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.person.Person;

@SuppressWarnings("serial")
public class PersonButton extends JButton implements ActionListener {

	private Person p;
	private PersonViewer pw;

	public PersonButton(Person p, PersonViewer pw) {
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
