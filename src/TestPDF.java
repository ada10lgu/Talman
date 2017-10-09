import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public class TestPDF {

	public static void main(String args[]) {
		// Get a file from the command line to open
		String filePath = "data/annex/bilaga_vtm_2017_1.pdf";

		// open the file
		Document document = new Document();
		try {
			document.setFile(filePath);
		} catch (Exception ex) {
			System.out.println("Error parsing PDF document " + ex);
		}

		// save page captures to file.
		float scale = 1.0f;
		float rotation = 0f;

		// Paint each pages content to an image and
		// write the image to file
		for (int i = 0; i < document.getNumberOfPages(); i++) {
			BufferedImage image = (BufferedImage) document.getPageImage(i,
					GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX,
					rotation, scale);
			RenderedImage rendImage = image;
			try {
				System.out.println(" capturing page " + i);
				File file = new File("imageCapture1_" + i + ".png");
				ImageIO.write(rendImage, "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image.flush();
		}

		// clean up resources
		document.dispose();
	}
}