package gui.controll.tabs.agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.agenda.Agenda;

@SuppressWarnings("serial")
public class DeleteButton extends JButton implements ActionListener, Observer {

	private Agenda agenda;

	public DeleteButton(Agenda agenda) {
		super("Delete item");
		this.agenda = agenda;
		addActionListener(this);
		agenda.addObserver(this);
		update(null,null);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (agenda.getActive() == null)
			setEnabled(false);
		else
			setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		agenda.removeActive();

	}

}
