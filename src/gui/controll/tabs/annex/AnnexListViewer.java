package gui.controll.tabs.annex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.annex.Annex;
import model.annex.AnnexList;

@SuppressWarnings("serial")
public class AnnexListViewer extends JPanel implements Observer {

	private AnnexList al;
	private AnnexViewer aw;
	
	public AnnexListViewer(AnnexList al, AnnexViewer aw) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.al = al;
		this.aw = aw;
		al.addObserver(this);
		updateList();
	}
	
	
	private void updateList() {
		removeAll();
		for (Annex a : al)
			add(new AnnexView(a));
		updateUI();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {

		updateList();
		
	}

	private class AnnexView extends JPanel implements ActionListener {

		private Annex a;

		public AnnexView(Annex a) {
			this.a = a;
			JButton jl = new JButton(a.getTitle());
			add(jl);
			jl.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			aw.showClaim(a);

		}

	}
	
}
