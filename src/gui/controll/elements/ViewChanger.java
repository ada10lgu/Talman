package gui.controll.elements;

import gui.controll.KeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Heidi;
import model.Heidi.State;

@SuppressWarnings("serial")
public class ViewChanger extends JButton implements ActionListener {

	private Heidi model;
	private State state;
	
	public ViewChanger(Heidi model, KeyListener kl, String title, String key, State state) {
		super(title + " ["+key+"]");
		this.model = model;
		this.state = state;
		addActionListener(this);
		kl.addActionListner(key, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setState(state);
	}
}
