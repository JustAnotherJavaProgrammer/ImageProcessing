package main;

import core.Luminanz;
import de.informatics4kids.Picture;

public class LuminanzDemo {

	public static void main(String[] args) {
		QuickView.showPicture(
				Luminanz.getGrayscale(new Picture("/home/student/Downloads/Panorama-Schulhof-mit-Text.png")));
	}

}
