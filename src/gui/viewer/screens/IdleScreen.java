package gui.viewer.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IdleScreen extends JPanel {
	private BufferedImage img;

	public IdleScreen() {
		img = null;
		try {
			img = ImageIO.read(new File("img/idle.jpg"));
		} catch (IOException e) {
			System.err.println("Could not load image file!");
			System.exit(1);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		Color bg = new Color(img.getRGB(0, 0));
		g.setColor(bg);
		g.fillRect(0, 0, w, h);
		
		double imageScale = getScale(img.getWidth(), img.getHeight());
		double screenScale = getScale(w, h);
		
		int i_x = 0;
		int i_y = 0;
		int i_w = w;
		int i_h = h;
		
		
		if (imageScale < screenScale) {
			i_w = (int) (imageScale * i_h);
		} else {
			i_h = (int) (i_w/imageScale);					
		}
		
		i_w *= 0.7;
		i_h *= 0.7;
		
		i_x = (w-i_w)/2;
		i_y = (h-i_h)/2;
		
		g.drawImage(img,i_x,i_y,i_w,i_h,null);
	}
	
	
	private double getScale(double w, double h) {
		return w/h;
	}

}
