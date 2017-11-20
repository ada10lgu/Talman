package gui.controll.tabs.agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.agenda.Agenda;

@SuppressWarnings("serial")
public class SaveButton extends JButton implements ActionListener {

	private Agenda agenda;

	public SaveButton(Agenda agenda) {
		super("Save to file");
		this.agenda = agenda;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		agenda.saveAll();
	}

}
