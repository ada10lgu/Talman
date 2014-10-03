package gui.controll;

import gui.controll.elements.TitleChanger;
import gui.controll.elements.TurnOnAgendaButton;
import gui.controll.elements.TurnOnIdleButton;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import model.Heidi;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	public Controller(Heidi model) {
		setSize(500, 200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLayout(new FlowLayout());
		add(new TurnOnAgendaButton(model));
		add(new TurnOnIdleButton(model));
		add(new TitleChanger(model));

		setVisible(true);
	}

}
