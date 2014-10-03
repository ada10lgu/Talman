package gui.controll.tabs.agenda;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.agenda.Agenda;
import model.agenda.Item;

@SuppressWarnings("serial")
public class ItemEditViewer extends JPanel implements Observer, ActionListener {

	private Agenda agenda;

	private JTextField number;
	private JTextField name;
	private JTextField type;
	private JTextField annex;
	private JButton updateButton;
	private JCheckBox accepted;
	
	public ItemEditViewer(Agenda agenda) {
		this.agenda = agenda;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		number = new JTextField();
		name = new JTextField();
		type = new JTextField();
		annex = new JTextField();

		add(new JLabel("Number:"));
		add(number);
		add(new JLabel("Name:"));
		add(name);
		add(new JLabel("Type:"));
		add(type);
		add(new JLabel("Annex:"));
		add(annex);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		bottomPanel.add(updateButton);
		accepted = new JCheckBox("Accepted");
		bottomPanel.add(accepted);
		add(bottomPanel);

		update(null,null);
		agenda.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Item i = agenda.getActive();

		if (i == null) {
			number.setEnabled(false);
			number.setText("");
			name.setEnabled(false);
			name.setText("");
			type.setEnabled(false);
			type.setText("");
			annex.setEnabled(false);
			annex.setText("");
			accepted.setEnabled(false);
			accepted.setSelected(false);
		
			updateButton.setEnabled(false);
		} else {
			number.setEnabled(true);
			number.setText(i.getNumber());
			name.setEnabled(true);
			name.setText(i.getName());
			type.setEnabled(true);
			type.setText(i.getType());
			annex.setEnabled(true);
			annex.setText(i.getAnnex());
			accepted.setEnabled(true);
			accepted.setSelected(i.isAccepted());
			updateButton.setEnabled(true);
			
		}
		updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String number = this.number.getText();
		String name = this.name.getText();
		String type = this.type.getText();
		String annex = this.annex.getText();
		boolean accepted = this.accepted.isSelected();
		try {
			agenda.updateActive(number, name, type, annex,accepted);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Could not parse number!");
		}
	}

}
