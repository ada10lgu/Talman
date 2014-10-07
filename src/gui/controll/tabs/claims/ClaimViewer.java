package gui.controll.tabs.claims;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.claim.Claim;
import model.claim.ClaimList;

@SuppressWarnings("serial")
public class ClaimViewer extends JPanel implements Observer {

	private ClaimList cl;
	private Claim active;

	private JTextField name;
	private JTextField author;
	private JTextArea description;
	
	private UpdateButton updateButton;
	private ShowButton showButton;
	private DeleteButton deleteButton;

	public ClaimViewer(ClaimList cl) {
		this.cl = cl;
		cl.addObserver(this);
		name = new JTextField(10);
		author = new JTextField(10);
		description = new JTextArea(5, 10);

		updateButton = new UpdateButton();
		showButton = new ShowButton();
		deleteButton = new DeleteButton();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);
		buttonPanel.add(showButton);
		buttonPanel.add(deleteButton);
		

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Edit claim"));

		add(new JLabel("Name"));
		add(name);
		add(new JLabel("Author"));
		add(author);
		add(new JLabel("Description"));
		add(new JScrollPane(description));
		add(buttonPanel);

		updateData();
	}

	public void showClaim(Claim c) {
		active = c;
		updateData();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (!cl.exists(active))
			active = null;
		updateData();
	}

	private void updateData() {
		if (active == null) {
			name.setEnabled(false);
			name.setText("");
			author.setEnabled(false);
			author.setText("");
			description.setEnabled(false);
			description.setEditable(false);
			description.setText("");
			updateButton.setEnabled(false);
			showButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
		} else {
			name.setEnabled(true);
			name.setText(active.getName());
			author.setEnabled(true);
			author.setText(active.getAuthor());
			description.setEnabled(true);
			description.setEditable(true);
			description.setText(active.getDesctiption());
			updateButton.setEnabled(true);
			showButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
	}

	private class UpdateButton extends JButton implements ActionListener {

		public UpdateButton() {
			super("Update");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			active.update(name.getText(), author.getText(),
					description.getText());

		}
	}

	private class ShowButton extends JButton implements ActionListener {

		public ShowButton() {
			super("Show");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cl.setActive(active);
		}
	}

	private class DeleteButton extends JButton implements ActionListener {

		public DeleteButton() {
			super("Delete");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cl.removeClaim(active);
		}
	}

}
