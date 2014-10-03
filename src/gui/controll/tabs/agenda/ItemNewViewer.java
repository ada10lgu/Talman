package gui.controll.tabs.agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.agenda.Agenda;

@SuppressWarnings("serial")
public class ItemNewViewer extends JPanel implements ActionListener {

	private Agenda agenda;

	private JTextField number;
	private JTextField name;
	private JTextField type;
	private JTextField annex;
	private JButton updateButton;

	public ItemNewViewer(Agenda agenda) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.agenda = agenda;
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

		updateButton = new JButton("Add new item");
		updateButton.addActionListener(this);
		add(updateButton);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String number = this.number.getText();
		String name = this.name.getText();
		String type = this.type.getText();
		String annex = this.annex.getText();
		try {
			agenda.newItem(number, name, type, annex);
			
			this.number.setText("");
			this.name.setText("");
			this.type.setText("");
			this.annex.setText("");
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Could not parse number!");
		}
		

	}
}
