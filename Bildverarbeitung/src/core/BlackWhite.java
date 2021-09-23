package core;
import java.awt.Color;

import de.informatics4kids.Picture;

public class BlackWhite {

	public static Picture toBW(Picture pic, int threshold) {
		Picture bw = new Picture(pic.widthX(), pic.heightY());
		for (int y = 0; y < pic.heightY(); y++) {
			for (int x = 0; x < pic.widthX(); x++) {
				bw.setColor(x, y, BlackWhite.colorToBW(pic.getColor(x, y), threshold));
			}
		}
		return bw;
	}

	public static int luminanceToBW(int l, int threshold) {
		if (l >= threshold)
			return 255;
		return 0;
	}

	public static Color colorToBW(Color c, int threshold) {
		int bw = BlackWhite.luminanceToBW(c.getGreen(), threshold);
		return new Color(bw, bw, bw);
	}

}
