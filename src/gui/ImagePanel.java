package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image i;

	public ImagePanel(Image i) {
		this.i = i;
	}

	@Override
	public void paint(Graphics g) {
		if (i != null) {
			int x = 0;
			int y = 0;
			int w = getWidth();
			int h = getHeight();

			double screenRatio = (double) getWidth() / getHeight();
			double imageRatio = (double) i.getWidth(null) / i.getHeight(null);

			System.out.printf("screen %f%nimage %f%n", screenRatio, imageRatio);

			if (imageRatio < screenRatio) {
				w = (int) (imageRatio * h);
				x = getWidth() / 2 - w / 2;
			} else {
				h = (int) (w / imageRatio);
				y = getHeight() / 2 - h / 2;
			}

			g.drawImage(i, x, y, w, h, null);
		}
	}

	public void setImage(Image i) {
		this.i = i;
		repaint();
	}
}
