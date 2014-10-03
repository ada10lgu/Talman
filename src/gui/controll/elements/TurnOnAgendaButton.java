package gui.controll.elements;

import gui.controll.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Heidi;
import model.Heidi.State;

@SuppressWarnings("serial")
public class TurnOnAgendaButton extends JButton implements ActionListener {

	private Heidi model;

	public TurnOnAgendaButton(Heidi model, KeyListener kl) {
		super("Agenda [F2]");
		this.model = model;
		addActionListener(this);
		kl.addActionListner("F2", this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setState(State.AGENDA);
	}
}
