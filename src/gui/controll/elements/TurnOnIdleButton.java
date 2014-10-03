package gui.controll.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Heidi;
import model.Heidi.State;

@SuppressWarnings("serial")
public class TurnOnIdleButton extends JButton implements ActionListener {

	private Heidi model;

	public TurnOnIdleButton(Heidi model) {
		super("Idle");
		this.model = model;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setState(State.IDLE);
	}

}
