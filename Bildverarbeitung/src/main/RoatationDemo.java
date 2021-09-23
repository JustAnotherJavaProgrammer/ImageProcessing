package main;

import core.Rotation;
import de.informatics4kids.Picture;

public class RoatationDemo {

	public static void main(String[] args) {
		QuickView.showPicture(Rotation
				.rotateImage(new Picture("/home/student/Downloads/Panorama-Schulhof-mit-Text.png"), Math.PI * 0.25));
	}

}
