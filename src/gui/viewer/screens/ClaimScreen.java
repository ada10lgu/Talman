package gui.viewer.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.claim.Claim;
import model.claim.ClaimList;

@SuppressWarnings("serial")
public class ClaimScreen extends JPanel implements Observer {

	private final Color BG_COLOR = Color.WHITE;
	private final Color TEXT_COLOR = Color.BLACK;
	private final Color TITLE_COLOR = Color.BLACK;
	private final Color AUTHOR_COLOR = Color.BLACK;

	private final Font TEXT_FONT = new Font("Verdana", Font.PLAIN, 24);;
	private final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 45);
	private final Font AUTHOR_FONT = new Font("Verdana", Font.PLAIN, 24);
	

	private final int TOP_MARGIN = 40;
	private final int LEFT_MARGIN = 50;
	private final int RIGHT_MARGIN = 50;

	private final int Y_SPACING = 30;
	private final int Y_TEXT_SPACING = 5;

	private ClaimList cl;

	public ClaimScreen(ClaimList cl) {
		this.cl = cl;
		cl.addObserver(this);
	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		g.setColor(BG_COLOR);
		g.fillRect(0, 0, w, h);

		Claim c = cl.getActiveClaim();

		if (c == null) {
			g.setFont(TITLE_FONT);
			g.setColor(TITLE_COLOR);
			FontMetrics fm = g.getFontMetrics();
			int title_y = TOP_MARGIN + fm.getHeight();
			g.drawString("None selected", LEFT_MARGIN, title_y);
		} else {
			int startY = TOP_MARGIN;
			{
				g.setFont(TITLE_FONT);
				g.setColor(TITLE_COLOR);
				FontMetrics fm = g.getFontMetrics();
				startY += fm.getHeight();
				g.drawString(c.getName(), LEFT_MARGIN, startY);
			}
			startY += Y_SPACING;

			{
				g.setFont(TEXT_FONT);
				g.setColor(TEXT_COLOR);
				FontMetrics fm = g.getFontMetrics();
				String[] rows = c.getDesctiption().split("\n");

				for (String s : rows) {
					for (String row : splitString(s, fm, w - LEFT_MARGIN
							- RIGHT_MARGIN)) {
						startY += fm.getHeight();
						g.drawString(row, LEFT_MARGIN, startY);
					}
				}
			}
			startY += 2 * Y_SPACING;
			{
				g.setFont(AUTHOR_FONT);
				g.setColor(AUTHOR_COLOR);
				FontMetrics fm = g.getFontMetrics();
				startY += fm.getHeight();
				g.drawString(c.getAuthor(), LEFT_MARGIN, startY);
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
