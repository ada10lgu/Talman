package gui.controll.tabs.agenda;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.agenda.Agenda;
import model.agenda.Item;

@SuppressWarnings("serial")
public class AgendaList extends JPanel implements Observer {

	private Agenda agenda;

	public AgendaList(Agenda agenda) {
		this.agenda = agenda;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		agenda.addObserver(this);
		updateList();
	}

	private void updateList() {
		removeAll();
		add(new NoItem(agenda));
		for (Item i : agenda) {
			add(new AgendaItem(i, agenda));
		}
		updateUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateList();
	}

	private class AgendaItem extends JPanel implements ActionListener, Observer {
		private Item item;
		private Agenda agenda;

		private JCheckBox rb;

		public AgendaItem(Item item, Agenda agenda) {
			this.item = item;
			this.agenda = agenda;
			setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel l = new JLabel(item.toString());
			rb = new JCheckBox();
			rb.addActionListener(this);
			add(rb);
			add(l);
			agenda.addObserver(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			agenda.setActive(item);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (agenda.isActive(item))
				rb.setSelected(true);
			else
				rb.setSelected(false);
			rb.updateUI();
		}
	}
	private class NoItem extends JPanel implements ActionListener, Observer {
		private Agenda agenda;

		private JCheckBox rb;

		public NoItem(Agenda agenda) {
			this.agenda = agenda;
			setLayout(new FlowLayout(FlowLayout.LEFT));
			rb = new JCheckBox();
			rb.addActionListener(this);
			add(rb);
			agenda.addObserver(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			agenda.setActive(null);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (agenda.isActive(null))
				rb.setSelected(true);
			else
				rb.setSelected(false);
			rb.updateUI();
		}
	}
}
