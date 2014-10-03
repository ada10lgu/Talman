package gui.controll.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.Heidi;

@SuppressWarnings("serial")
public class TitleChanger extends JTextField implements ActionListener {
	
	private Heidi model;
	
	public TitleChanger(Heidi model) {
		super(10);
		this.model = model;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setTitle(getText());
	}
}
