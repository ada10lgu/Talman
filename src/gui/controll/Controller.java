package gui.controll;

import gui.controll.elements.TurnOnAgendaButton;
import gui.controll.elements.TurnOnIdleButton;
import gui.controll.tabs.agenda.AgendaTab;
import gui.controll.tabs.speakers.SpeakersTab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Heidi;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	public Controller(Heidi model) {
		KeyListener kl = new KeyListener(this);
		
		
		setSize(500, 200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLayout(new BorderLayout());

		JPanel topPane = new JPanel();
		topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPane.add(new TurnOnIdleButton(model,kl));
		topPane.add(new TurnOnAgendaButton(model,kl));

		JTabbedPane tp = new JTabbedPane();
		tp.add(new AgendaTab(model),"Agenda");
		tp.add(new SpeakersTab(model),"Speakers");
		tp.add(new SpeakersTab(model),"Annex");
		
		
		
		add(topPane,BorderLayout.NORTH);
		add(tp,BorderLayout.CENTER);
		
		
		setVisible(true);
	}

}
