package gui.viewer.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SangScreen extends JPanel {

	private final Color BG_COLOR = Color.WHITE;
	private final Color TEXT_COLOR = Color.BLACK;
	private final Color TITLE_COLOR = Color.BLACK;

	private final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 16);;
	private final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 35);

	private final int TOP_MARGIN = 25;
	private final int LEFT_MARGIN = 50;
	private final int MIDDLE_MARGIN = 50;
	private final int Y_TEXT_SPACING = 0;

	private final String TITLE = "Rosa på bal";
	private ArrayList<String> text;

	private final File SANG_FILE = new File("rosa");

	public SangScreen() {
		text = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(SANG_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		while (s.hasNext()) {
			text.add(s.nextLine());
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());

		int startY = TOP_MARGIN;

		{
			g.setColor(TITLE_COLOR);
			g.setFont(TITLE_FONT);
			FontMetrics fm = g.getFontMetrics();

			startY += fm.getHeight();
			g.drawString(TITLE, LEFT_MARGIN, startY);
			startY += fm.getHeight();

		}

		g.setFont(TEXT_FONT);
		g.setColor(TEXT_COLOR);

		int y = startY;
		int x = LEFT_MARGIN;

		int w = 0;

		FontMetrics fm = g.getFontMetrics();
		for (String s : text) {

			g.drawString(s, x, y);

			y += fm.getHeight() + Y_TEXT_SPACING;
			w = Math.max(w, fm.stringWidth(s));
			if (y >= getHeight()) {
				y = startY;
				x += w + MIDDLE_MARGIN;
				w = 0;
			}

		}

	}
}
