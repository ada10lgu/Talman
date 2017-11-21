package gui.controll.tabs.person;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.person.Person;
import model.person.PersonList;

@SuppressWarnings("serial")
public class ListPanel extends JPanel implements Observer {

	private PersonList pl;
	private PersonViewer pw;

	public ListPanel(PersonList pl, PersonViewer pw) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.pl = pl;
		this.pw = pw;
		pl.addObserver(this);
		updateList();
	}

	private void updateList() {
		removeAll();
		for (Person p : pl)
			add(new PersonButton(p,pw));
		updateUI();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateList();
	}

}
