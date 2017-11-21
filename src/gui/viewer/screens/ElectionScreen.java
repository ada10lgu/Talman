package gui.viewer.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import model.election.Election;
import model.election.ElectionList;
import model.person.Person;

@SuppressWarnings("serial")
public class ElectionScreen extends JPanel implements Observer {

	private ElectionList el;

	private final Font NAME_FONT = new Font("Verdana", Font.PLAIN, 25);;
	private final Font TITLE_FONT = new Font("Verdana", Font.PLAIN, 35);

	public ElectionScreen(ElectionList el) {
		this.el = el;
		el.addObserver(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		Election e = el.getDisplay();

		if (e != null) {
			g.setColor(Color.BLACK);
			int h = 0;
			g.setFont(TITLE_FONT);
			FontMetrics fm = g.getFontMetrics();

			int titleW = fm.stringWidth(e.getTitle());

			h = (int) (fm.getHeight() * 2);

			g.drawString(e.getTitle(), getWidth() / 2 - titleW / 2, h);

			List<Person> participants = e.getParticipants();
			if (!participants.isEmpty()) {
				g.setFont(NAME_FONT);
				fm = g.getFontMetrics();
				int w = getWidth() / (participants.size()+2);

				int rectangleH = getHeight() - h;
				int imageBoxW = w - 20;
				int imageBoxH = rectangleH - fm.getHeight() * 4;
				int imageBoxY = h + 3 * fm.getHeight();

				for (int i = 0; i < participants.size(); i++) {
					Person p = participants.get(i);
					int x = w * (i+1);

					int nameW = fm.stringWidth(p.getName());
					g.drawString(p.getName(), x + w / 2 - nameW / 2, h + 2 * fm.getHeight());

					{
						Image img = p.getImage();
						if (img == null)
							continue;
						int ix = 0;
						int iy = 0;
						int iw = imageBoxW;
						int ih = imageBoxH;

						double screenRatio = (double) imageBoxW / imageBoxH;
						double imageRatio = (double) img.getWidth(null) / img.getHeight(null);

						if (imageRatio < screenRatio) {
							iw = (int) (imageRatio * ih);
							ix = imageBoxW / 2 - iw / 2;
						} else {
							ih = (int) (iw / imageRatio);
							iy = imageBoxH / 2 - ih / 2;
						}

						g.drawImage(img, ix + x + 10, iy + imageBoxY, iw, ih, null);
					}

				}
			}
		}

	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		updateUI();
		repaint();
	}

}
