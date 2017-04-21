package gui.controll.tabs.agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.TalmanModel;

@SuppressWarnings("serial")
public class TitleChanger extends JTextField implements ActionListener {
	
	private TalmanModel model;
	
	public TitleChanger(TalmanModel model) {
		super(10);
		this.model = model;
		addActionListener(this);
		
		setText(model.getTitle());
		actionPerformed(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.setTitle(getText());
	}
}
