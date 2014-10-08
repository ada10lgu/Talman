package model.annex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import standalone.pdf.PDFFile;

public class Annex {

	private PDFFile pdf;
	private String title;
	private int active;
	
	public Annex(File f) throws IOException {
		pdf = new PDFFile(f);
		System.out.println(f);
		title = f.getName();
	}

	public String getTitle() {
		return title;
	}
	public int getNumberOfPages() {
		return pdf.getNumberOfPages();
	}
	public void setActive(int active) {
		if (active >= getNumberOfPages())
			active = getNumberOfPages()-1;
		if (active <= 0)
			active = 0;
		this.active = active;
	}

	public BufferedImage getActivePage() throws IOException {
		return pdf.getPage(active);
	}
	
	
}
