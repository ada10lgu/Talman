package gui.viewer.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.annex.Annex;
import model.annex.AnnexList;

@SuppressWarnings("serial")
public class AnnexScreen extends JPanel implements Observer {

	private final Color BG_COLOR = Color.LIGHT_GRAY;
	private final Color TITLE_COLOR = Color.BLACK;

	private final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 35);

	private final String TITLE = "Annex";
	private final String ERROR_MESSAGE = "Problem reading PDF";

	private AnnexList al;

	public AnnexScreen(AnnexList al) {
		this.al = al;
		al.addObserver(this);

	}

	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, w, h);

		Annex a = al.getActive();
		if (a == null) {
			g.setFont(TITLE_FONT);
			FontMetrics fm = g.getFontMetrics();
			int y = h / 2 + fm.getHeight() / 2;
			int x = w / 2 - fm.stringWidth(TITLE) / 2;
			g.setColor(TITLE_COLOR);
			g.drawString(TITLE, x, y);
		} else {
			BufferedImage img = null;
			try {
				img = a.getActivePage();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (img == null) {
				g.setFont(TITLE_FONT);
				FontMetrics fm = g.getFontMetrics();
				int y = h / 2 + fm.getHeight() / 2;
				int x = w / 2 - fm.stringWidth(ERROR_MESSAGE) / 2;
				g.setColor(TITLE_COLOR);
				g.drawString(ERROR_MESSAGE, x, y);
				return;
			}
			int x = getWidth() / 2 - img.getWidth() / 2;

			int extra = getHeight() - img.getHeight();
			int y = (int) (extra * a.getPossition() / 100.0);

			g.drawImage(img, x, y, null);

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
