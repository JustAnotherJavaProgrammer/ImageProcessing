package gui;

import core.BlackWhite;
import de.informatics4kids.Picture;

public class BlackWhiteDemo {

	public static void main(String[] args) {
		QuickView.showPicture(
				BlackWhite.toBW(new Picture("/home/student/Downloads/Panorama-Schulhof-mit-Text.png"), 128));
	}

}
