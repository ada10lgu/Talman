package gui.controll.tabs.claims;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.claim.Claim;
import model.claim.ClaimList;

@SuppressWarnings("serial")
public class ClaimListView extends JPanel implements Observer {

	private ClaimViewer cw;
	private ClaimList cl;

	public ClaimListView(ClaimList cl, ClaimViewer cw) {
		this.cl = cl;
		this.cw = cw;
		cl.addObserver(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Claims"));
		updateList();
	}

	private void updateList() {
		removeAll();
		for (Claim c : cl)
			add(new ClaimView(c));
		updateUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateList();
	}

	private class ClaimView extends JPanel implements ActionListener {

		private Claim c;

		public ClaimView(Claim c) {
			this.c = c;
			JButton jl = new JButton(c.getName());
			add(jl);
			jl.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cw.showClaim(c);

		}

	}
}
