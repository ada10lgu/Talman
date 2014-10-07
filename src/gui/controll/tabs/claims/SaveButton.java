package gui.controll.tabs.claims;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.claim.ClaimList;

@SuppressWarnings("serial")
public class SaveButton extends JButton implements ActionListener {

	private ClaimList cl;

	public SaveButton(ClaimList cl) {
		super("Save");
		this.cl = cl;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cl.save();

	}
}
