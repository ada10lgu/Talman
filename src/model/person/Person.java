package model.person;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Person {

	ArrayList<String> data;
	private PersonList pl;
	private Image image;

	public Person(ArrayList<String> data, PersonList pl) {
		this.data = data;
		this.pl = pl;
		image = loadImage(getImageURL());
	}

	private Image loadImage(String imageURL) {
		Image image = null;
		try {
			URL url = new URL(imageURL);
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("Could not load image");
		}
		return image;
	}

	public String getName() {
		return data.get(1);
	}

	public String getSTIL() {
		return data.get(0);
	}

	public Image getImage() {
		return image;
	}

	public String getImageURL() {
		return data.get(2);
	}

	public void setImage(String newImage) {
		Image i = loadImage(newImage);
		if (i != null) {
			data.set(2, newImage);
			image = i;
			pl.save();
		}
	}

}
