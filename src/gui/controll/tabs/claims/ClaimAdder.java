package gui.controll.tabs.claims;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.claim.ClaimList;

@SuppressWarnings("serial")
public class ClaimAdder extends JPanel implements ActionListener {

	private ClaimList cl;

	private JTextField name;
	private JTextField author;
	private JTextArea description;

	public ClaimAdder(ClaimList cl) {
		this.cl = cl;
		name = new JTextField(10);
		author = new JTextField(10);
		description = new JTextArea(5, 10);

		JPanel buttonPanel = new JPanel();
		JButton button = new JButton("Add");
		button.addActionListener(this);
		buttonPanel.add(button);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Edit claim"));

		add(new JLabel("Name"));
		add(name);
		add(new JLabel("Author"));
		add(author);
		add(new JLabel("Description"));
		add(new JScrollPane(description));
		add(buttonPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cl.addClaim(name.getText(), author.getText(), description.getText());
		name.setText("");
		author.setText("");
		description.setText("");
	}
}
