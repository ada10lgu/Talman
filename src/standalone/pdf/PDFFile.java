package standalone.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFFile {

	private PDDocument doc;

	public PDFFile(File f) throws IOException {

		doc = PDDocument.load(f);

	}

	public int getNumberOfPages() {
		return doc.getNumberOfPages();
	}

	public BufferedImage getPage(int n) throws IOException {
		PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(n);
		return page.convertToImage();
	}

}
