package gui.controll.tabs.agenda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Heidi;
import model.agenda.Agenda;

@SuppressWarnings("serial")
public class AgendaTab extends JPanel {

	public AgendaTab(Heidi model) {
		Agenda agenda = model.getAgenda();
		setLayout(new GridLayout(1, 2));

		JScrollPane jsp = new JScrollPane(new AgendaList(agenda));
		jsp.setBorder(BorderFactory.createTitledBorder("Active"));
		add(jsp);

		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
		topPanel.add(new TitleChanger(model));
		topPanel.add(new SaveButton(agenda));
		topPanel.add(new DeleteButton(agenda));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		
		JPanel edit = new ItemEditViewer(agenda);
		edit.setBorder(BorderFactory.createTitledBorder("Item"));
		JPanel add = new ItemNewViewer(agenda); 
		add.setBorder(BorderFactory.createTitledBorder("Item"));
		
		
		bottomPanel.add(edit);
		bottomPanel.add(add);
		
		rightPanel.add(topPanel, BorderLayout.NORTH);
		rightPanel.add(bottomPanel, BorderLayout.CENTER);

		add(rightPanel);
	}
}
