package gui.controll.tabs.election;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.ImagePanel;
import model.person.Person;

@SuppressWarnings("serial")
public class ElectionViewer extends JPanel implements ActionListener {

	private Person active;
	private JLabel name;
	private JLabel stil;
	private ImagePanel image;
	private JTextField url;

	public ElectionViewer() {
		setLayout(null);
		setBackground(Color.WHITE);
		name = new JLabel();
		stil = new JLabel();
		image = new ImagePanel(null);
		url = new JTextField(10);
		url.addActionListener(this);

		add(name);
		add(stil);
		add(image);
		add(url);
	}

	@Override
	public void repaint() {
		if (name != null) {
			System.out.println("paint");
			int h = name.getPreferredSize().height;

			int nameW = name.getPreferredSize().width;

			name.setBounds(getWidth() / 2 - nameW / 2, h, nameW, h);

			int stilW = stil.getPreferredSize().width;

			stil.setBounds(getWidth() / 2 - stilW / 2, 2 * h, stilW, h);

			int imageH = getHeight() - h * 7;

			image.setBounds(0, h * 4, getWidth(), imageH);

			int urlW = url.getPreferredSize().width;

			url.setBounds(getWidth() / 2 - urlW / 2, getHeight() - h * 2, urlW, h);
		}

		super.repaint();
	}

	public void setActive(Person p) {
		active = p;
		if (p != null) {
			name.setText(p.getName());
			stil.setText(p.getSTIL());
			image.setImage(p.getImage());
			url.setText(p.getImageURL());
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		active.setImage(url.getText());
		setActive(active);
	}

}
