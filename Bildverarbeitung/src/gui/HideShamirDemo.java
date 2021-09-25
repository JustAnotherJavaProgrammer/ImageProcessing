package gui;

import core.BlackWhite;
import core.HideShamir;
import de.informatics4kids.Picture;

public class HideShamirDemo {

	public static void main(String[] args) {
		Picture bw = BlackWhite.toBW(new Picture("/home/student/Downloads/Panorama-Schulhof-mit-Text.png"), 128);
		Picture noise = HideShamir.getNoise(bw.widthX(), bw.heightY());
		Picture derivedNoise = HideShamir.xorPics(noise, bw);
		Picture preShamir = HideShamir.preShamirDecrypt(noise, derivedNoise);
		Picture decrypted = HideShamir.xorPics(derivedNoise, noise);
		QuickView.showPicture(bw);
		QuickView.showPicture(noise);
		QuickView.showPicture(derivedNoise);
		QuickView.showPicture(preShamir);
		QuickView.showPicture(decrypted);
	}

}
