package gui.controll;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.controll.elements.ViewChanger;
import gui.controll.tabs.agenda.AgendaTab;
import gui.controll.tabs.annex.AnnexTab;
import gui.controll.tabs.claims.ClaimTab;
import gui.controll.tabs.election.ElectionTab;
import gui.controll.tabs.person.PersonTab;
import gui.controll.tabs.speakers.SpeakersTab;
import model.TalmanModel;
import model.TalmanModel.State;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	public Controller(TalmanModel model) {
		KeyListener kl = new KeyListener(this);

		setSize(500, 200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLayout(new BorderLayout());

		JPanel topPane = new JPanel(new GridLayout(1, 2));
		JPanel leftTopPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		leftTopPane.add(new ViewChanger(model, kl, "Idle", "F1", State.IDLE));
		leftTopPane.add(new ViewChanger(model, kl, "Agenda", "F2", State.AGENDA));
		leftTopPane.add(new ViewChanger(model, kl, "Annex", "F3", State.ANNEX));
		leftTopPane.add(new ViewChanger(model, kl, "Claims", "F4", State.CLAIM));
		leftTopPane.add(new ViewChanger(model, kl, "Rosa på bal", "F5", State.SING));
		leftTopPane.add(new ViewChanger(model, kl, "Val", "F6", State.ELECTION));

		JPanel rightTopPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		rightTopPane.add(new StateViewer(model));
		rightTopPane.add(new Clock());

		topPane.add(leftTopPane);
		topPane.add(rightTopPane);

		JTabbedPane tp = new JTabbedPane();
		tp.add(new AgendaTab(model), "Agenda");
		tp.add(new SpeakersTab(model), "Speakers");
		tp.add(new AnnexTab(model.getAnnexList()), "Annex");
		tp.add(new ClaimTab(model.getClaimList()), "Claims");
		tp.add(new PersonTab(model.getPersonList()), "Persons");
		tp.add(new ElectionTab(model.getElectionList()), "Elections");

		add(topPane, BorderLayout.NORTH);
		add(tp, BorderLayout.CENTER);

		setVisible(true);
	}

}
