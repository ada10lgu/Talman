package model.annex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public class Annex implements Comparable<Annex> {

	private Document pdf;
	private String title;
	private int active;
	private float zoom;
	private int possition;
	private AnnexList al;

	public Annex(File f, AnnexList al) throws IOException {
		this.al = al;
		pdf = new Document();
		pdf.setFile(f.getPath());

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
		return (BufferedImage) pdf.getPageImage(active, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0f, zoom);
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
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

	@Override
	public int compareTo(Annex a) {
		return title.compareTo(a.title);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Annex) {
			return ((Annex) obj).title.equals(title);
		}
		return false;
	}

}
