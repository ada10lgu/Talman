package model.annex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import standalone.pdf.PDFFile;

public class Annex {

	private PDFFile pdf;
	private String title;
	private int active;
	private double zoom;
	private int possition;
	private AnnexList al;

	public Annex(File f, AnnexList al) throws IOException {
		this.al = al;
		pdf = new PDFFile(f);
		title = f.getName();
		zoom = 1;

	}

	public String getTitle() {
		return title;
	}

	public int getNumberOfPages() {
		return pdf.getNumberOfPages();
	}

	public void setActive(int active) {
		if (active >= getNumberOfPages())
			active = getNumberOfPages() - 1;
		if (active <= 0)
			active = 0;
		this.active = active;
	}

	public BufferedImage getActivePage() throws IOException {
		return pdf.getPage(active);
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
		al.update();
	}

	public int getPossition() {
		return possition;
	}

	public void setPossition(int possition) {
		if (possition < 0)
			possition = 0;
		if (possition > 100)
			possition = 100;
		this.possition = possition;
		al.update();
	}

	public void setPage(int value) {
		if (value < 1)
			active = 1;
		if (value >= getNumberOfPages())
			active = getNumberOfPages() - 1;
		active = value;
		al.update();
	}

	public int getPage() {
		return active;
	}

}
