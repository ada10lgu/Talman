package gui.controll.tabs.claims;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.claim.ClaimList;

@SuppressWarnings("serial")
public class ClaimTab extends JPanel {

	public ClaimTab(ClaimList cl) {

		ClaimViewer cw = new ClaimViewer(cl);

		ClaimListView clv = new ClaimListView(cl, cw);

		setLayout(new GridLayout(1, 2));
		
		JPanel rightScreen = new JPanel();
		rightScreen.setLayout(new BoxLayout(rightScreen, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createTitledBorder("Misc"));
		topPanel.add(new SaveButton(cl));
		
		rightScreen.add(topPanel);
		rightScreen.add(cw);
		rightScreen.add(new ClaimAdder(cl));
		
		
		
		add(new JScrollPane(clv));
		add(new JScrollPane(rightScreen));

	}
}
