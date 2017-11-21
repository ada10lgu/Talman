package gui.controll.tabs.election;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.election.Election;
import model.election.ElectionList;

@SuppressWarnings("serial")
public class ListPanel extends JPanel implements Observer {

	private ElectionList el;
	private ElectionViewer ew;

	public ListPanel(ElectionList el, ElectionViewer ew) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.el = el;
		this.ew = ew;
		el.addObserver(this);
		updateList();
	}

	private void updateList() {
		removeAll();
		for (Election e : el)
			add(new ElectionButton(e, ew));
		updateUI();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateList();
		System.out.println("List updated");
	}

}
