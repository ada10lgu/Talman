package gui.viewer;

import gui.viewer.screens.AgendaScreen;
import gui.viewer.screens.IdleScreen;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Heidi;

@SuppressWarnings("serial")
public class Viewer extends JFrame implements Observer {

	private Heidi model;
	private JPanel container;
	
	private JLabel label;
	private IdleScreen idle;
	private AgendaScreen agenda;
	
	public Viewer(Heidi model) {
		this.model= model;
		setSize(500, 200);
		
		
		idle = new IdleScreen();
		agenda = new AgendaScreen(model);
		label = new JLabel();

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(idle,BorderLayout.CENTER);
		add(container,BorderLayout.CENTER);
		
		
		
		model.addObserver(this);
		update(null,null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		setTitle(model.getTitle());
		
		container.removeAll();
		switch (model.getState()) {
		case IDLE:
			container.add(idle,BorderLayout.CENTER);
			
			break;
		case AGENDA:
			container.add(agenda,BorderLayout.CENTER);
			break;
		default:
			label.setText("Detta borde inte ske...");
			container.add(label,BorderLayout.CENTER);
		}
		container.updateUI();
	}

}
