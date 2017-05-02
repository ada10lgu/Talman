package gui.controll;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.TalmanModel;

@SuppressWarnings("serial")
public class StateViewer extends JLabel implements Observer {

	private TalmanModel model;

	public StateViewer(TalmanModel model) {
		this.model = model;
		setFont(new Font("Verdana", Font.PLAIN, 20));
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		setText(model.getState().toString() + "     ");
	}
}