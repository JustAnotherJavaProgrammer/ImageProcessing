package core;
import java.awt.Color;

import de.informatics4kids.Picture;

public class Luminanz {

	public static Picture getGrayscale(Picture pic) {
		Picture grayscale = new Picture(pic.widthX(), pic.heightY());
		for (int y = 0; y < pic.heightY(); y++) {
			for (int x = 0; x < pic.widthX(); x++) {
				grayscale.setColor(x, y, Luminanz.grayFromLuminance(colorToLuminance(pic.getColor(x, y))));
			}
		}
		return grayscale;
	}

	public static int colorToLuminance(Color c) {
		return (int) (0.3 * c.getRed() + 0.59 * c.getGreen() + 0.11 * c.getBlue());
	}

	public static Color grayFromLuminance(int l) {
		return new Color(l, l, l);
	}

}
