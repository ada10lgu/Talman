package gui.controll.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Heidi;
import model.Heidi.State;

@SuppressWarnings("serial")
public class TurnOnAgendaButton extends JButton implements ActionListener {

	private Heidi model;

	public TurnOnAgendaButton(Heidi model) {
		super("Agenda");
		this.model = model;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setState(State.AGENDA);
	}

}
