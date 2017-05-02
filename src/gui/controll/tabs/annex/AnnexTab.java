package gui.controll.tabs.annex;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.annex.AnnexList;

@SuppressWarnings("serial")
public class AnnexTab extends JPanel {

	public AnnexTab(AnnexList al) {
		AnnexViewer aw = new AnnexViewer(al);
		AnnexListViewer alv = new AnnexListViewer(al, aw);

		setLayout(new GridLayout(1, 2));

		JPanel rightScreen = new JPanel();
		rightScreen.setLayout(new BoxLayout(rightScreen, BoxLayout.Y_AXIS));

		
		add(new JScrollPane(alv));
		add(new JScrollPane(aw));
	}
}