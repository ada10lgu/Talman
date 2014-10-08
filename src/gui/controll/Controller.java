package gui.controll;

import gui.controll.elements.ViewChanger;
import gui.controll.tabs.agenda.AgendaTab;
import gui.controll.tabs.annex.AnnexTab;
import gui.controll.tabs.claims.ClaimTab;
import gui.controll.tabs.speakers.SpeakersTab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Heidi;
import model.Heidi.State;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	public Controller(Heidi model) {
		KeyListener kl = new KeyListener(this);

		setSize(500, 200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLayout(new BorderLayout());

		JPanel topPane = new JPanel();
		topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPane.add(new ViewChanger(model, kl, "Idle", "F1", State.IDLE));
		topPane.add(new ViewChanger(model, kl, "Agenda", "F2", State.AGENDA));
		topPane.add(new ViewChanger(model, kl, "Annex", "F3", State.ANNEX));
		topPane.add(new ViewChanger(model, kl, "Claims", "F4", State.CLAIM));

		JTabbedPane tp = new JTabbedPane();
		tp.add(new AgendaTab(model), "Agenda");
		tp.add(new SpeakersTab(model), "Speakers");
		tp.add(new AnnexTab(model.getAnnexList()), "Annex");
		tp.add(new ClaimTab(model.getClaimList()), "Claims");

		add(topPane, BorderLayout.NORTH);
		add(tp, BorderLayout.CENTER);

		setVisible(true);
	}

}
