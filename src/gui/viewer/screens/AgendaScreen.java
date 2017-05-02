package gui.viewer.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.TalmanModel;
import model.agenda.Agenda;
import model.agenda.Item;

@SuppressWarnings("serial")
public class AgendaScreen extends JPanel implements Observer {

	private final Color BG_COLOR = Color.WHITE;
	private final Color TEXT_COLOR = new Color(0xF280A1);
	private final Color TITLE_COLOR = Color.BLACK;
	private final Color NOT_ACCEPTED_COLOR = Color.BLACK;

	private final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 12);;
	private final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 35);

	private final int TOP_MARGIN = 25;
	private final int LEFT_MARGIN = 50;
	private final int RIGHT_MARGIN = 50;

	private final int X_SPACING = 30;
	private final int Y_SPACING = 30;
	private final int Y_TEXT_SPACING = 0;

	private final int IMAGE_SIZE = 20;
	private final int IMAGE_SPACING = 5;

	private final int LOGO_SIZE = 200;

	private TalmanModel model;
	private Agenda agenda;
	private BufferedImage img;
	private BufferedImage logo;

	public AgendaScreen(TalmanModel model) {
		this.model = model;
		agenda = model.getAgenda();
		agenda.addObserver(this);
		img = null;
		try {
			img = ImageIO.read(new File("img/arrow.jpg"));
			logo = ImageIO.read(new File("img/idle.jpg"));
		} catch (IOException e) {
			System.err.println("Could not load image file!");
			System.exit(1);
		}

	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, w, h);

		{
			int l_x = w - 3 * LOGO_SIZE / 2;
			int l_y = h - LOGO_SIZE;
			int l_w = LOGO_SIZE * 2;
			int l_h = l_w;

			g.drawImage(logo, l_x, l_y, l_w, l_h, null);

		}

		int startY = TOP_MARGIN;

		{
			g.setColor(TITLE_COLOR);
			g.setFont(TITLE_FONT);
			FontMetrics fm = g.getFontMetrics();

			int titleStart = TOP_MARGIN + fm.getHeight();
			g.drawString(model.getTitle(), LEFT_MARGIN, titleStart);
			startY += fm.getHeight();

		}

		startY += Y_SPACING;

		{
			g.setColor(TEXT_COLOR);
			g.setFont(TEXT_FONT);
			FontMetrics fm = g.getFontMetrics();
			int nbrSize = 0;
			int nameSize = 0;
			int typeSize = 0;
			int annexSize = 0;
			for (Item i : agenda) {
				nbrSize = Math.max(nbrSize, fm.stringWidth(model
						.getParagraphSign() + i.getNumber()));
				nameSize = Math.max(nameSize, fm.stringWidth(i.getName()));
				typeSize = Math.max(typeSize, fm.stringWidth(i.getType()));
				annexSize = Math.max(annexSize, fm.stringWidth(i.getAnnex()));

			}

			int numberStart = LEFT_MARGIN;
			int nameStart = numberStart + nbrSize + X_SPACING;
			int typeStart = 0;
			int annexStart = 0;

			if (LEFT_MARGIN + RIGHT_MARGIN + nbrSize + nameSize + typeSize
					+ annexSize + X_SPACING * 3 < w) {
				typeStart = nameStart + nameSize + X_SPACING;
				annexStart = typeStart + typeSize + X_SPACING;
			} else {
				nameSize = w - LEFT_MARGIN - RIGHT_MARGIN - X_SPACING * 3
						- nbrSize - typeSize - annexSize;

				annexStart = w - RIGHT_MARGIN - annexSize;
				typeStart = annexStart - X_SPACING - typeSize;

			}

			int y = startY;

			for (Item i : agenda) {

				if (i.isAccepted())
					g.setColor(TEXT_COLOR);
				else
					g.setColor(NOT_ACCEPTED_COLOR);

				if (agenda.isActive(i)) {
					int im_x = LEFT_MARGIN - IMAGE_SIZE - IMAGE_SPACING;
					int im_y = y - fm.getAscent() / 2 - IMAGE_SIZE / 2;
					g.drawImage(img, im_x, im_y, IMAGE_SIZE, IMAGE_SIZE, null);
				}

				g.drawString(model.getParagraphSign() + i.getNumber(),
						numberStart, y);
				g.drawString(i.getType(), typeStart, y);
				g.drawString(i.getAnnex(), annexStart, y);

				if (fm.stringWidth(i.getName()) > nameSize) {
					for (String row : splitString(i.getName(), fm, nameSize)) {
						g.drawString(row, nameStart, y);
						y += fm.getHeight() + Y_TEXT_SPACING;
					}
				} else {
					g.drawString(i.getName(), nameStart, y);
					y += fm.getHeight() + Y_TEXT_SPACING;
				}

			}

		}

	}

	private ArrayList<String> splitString(String s, FontMetrics fm, int size) {
		ArrayList<String> rows = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (fm.stringWidth(sb.toString()) + fm.charWidth(c) > size) {
				rows.add(sb.toString());
				sb = new StringBuilder();
			}
			if (c != ' ' || sb.length() != 0)
				sb.append(c);
		}
		rows.add(sb.toString());
		return rows;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
