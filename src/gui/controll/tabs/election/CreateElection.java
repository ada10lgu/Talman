package gui.controll.tabs.election;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.election.ElectionList;

@SuppressWarnings("serial")
public class CreateElection extends JPanel {

	public CreateElection(final ElectionList el) {
		final JTextField tf = new JTextField(20);
		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				el.create(tf.getText());
				tf.setText("");
			}
		});
		add(tf);
	}
}
