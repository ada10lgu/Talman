package gui.controll.tabs.annex;

import javax.swing.JPanel;

import model.annex.Annex;
import model.annex.AnnexList;

@SuppressWarnings("serial")
public class AnnexViewer extends JPanel {

	private AnnexList al;
	
	public AnnexViewer(AnnexList al) {
		this.al = al;
	}
	
	public void showClaim(Annex a) {
		al.setActive(a);
	}

}
