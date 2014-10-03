package gui.viewer.screens;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.agenda.Agenda;
import model.agenda.Item;

@SuppressWarnings("serial")
public class AgendaScreen extends JPanel {
	
	Agenda agenda;
	
	public AgendaScreen(Agenda agenda) {
		this.agenda = agenda;
	}
	
	
	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.BLACK);
		
		int y = 10;
		int dy = 20;
		
		for (Item i : agenda) {
			g.drawString(i.toString(), 10, y);
			y+=dy;
		}
	
	}
}
