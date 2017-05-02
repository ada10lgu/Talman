package gui.viewer;

import gui.viewer.screens.AgendaScreen;
import gui.viewer.screens.AnnexScreen;
import gui.viewer.screens.ClaimScreen;
import gui.viewer.screens.IdleScreen;
import gui.viewer.screens.SangScreen;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TalmanModel;

@SuppressWarnings("serial")
public class Viewer extends JFrame implements Observer {

	private TalmanModel model;
	private JPanel container;

	private JLabel label;
	private IdleScreen idle;
	private AgendaScreen agenda;
	private AnnexScreen annex;
	private ClaimScreen claim;
	private SangScreen sing;

	public Viewer(TalmanModel model) {
		this.model = model;
		setSize(500, 200);

		idle = new IdleScreen();
		agenda = new AgendaScreen(model);
		claim = new ClaimScreen(model.getClaimList());
		annex = new AnnexScreen(model.getAnnexList());
		sing = new SangScreen();
		label = new JLabel();

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(idle, BorderLayout.CENTER);
		add(container, BorderLayout.CENTER);

		model.addObserver(this);
		update(null, null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		setTitle(model.getTitle());

		container.removeAll();
		switch (model.getState()) {
		case IDLE:
			container.add(idle, BorderLayout.CENTER);
			break;
		case AGENDA:
			container.add(agenda, BorderLayout.CENTER);
			break;
		case CLAIM:
			container.add(claim, BorderLayout.CENTER);
			break;
		case ANNEX:
			container.add(annex, BorderLayout.CENTER);
			break;
		case SING:
			container.add(sing, BorderLayout.CENTER);
			break;
		default:
			label.setText("Detta borde inte ske...");
			container.add(label, BorderLayout.CENTER);
		}
		container.updateUI();
	}

}
