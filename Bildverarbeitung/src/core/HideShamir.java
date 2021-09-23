package core;
import java.awt.Color;
import java.security.SecureRandom;

import de.informatics4kids.Picture;

public class HideShamir {

	public final static SecureRandom rand = new SecureRandom();

	public static Picture getNoiseInsecure(int width, int height) {
		return getNoise(width, height, false);
	}

	public static Picture getNoise(int width, int height) {
		return getNoise(width, height, true);
	}

	public static Picture getNoise(int width, int height, boolean secure) {
		Picture result = new Picture(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				result.setColor(x, y,
						(secure ? !rand.nextBoolean() : ((int) (Math.random() * 2) == 0)) ? Color.BLACK : Color.WHITE);
			}
		}
		return result;
	}

	public static Picture xorPics(Picture a, Picture b) {
		Picture result = new Picture(Math.max(a.widthX(), b.widthX()), Math.max(a.heightY(), b.heightY()));
		for (int y = 0; y < result.heightY(); y++) {
			for (int x = 0; x < result.widthX(); x++) {
				int aVal = (x < a.widthX() && y < a.heightY()) ? Luminanz.colorToLuminance(a.getColor(x, y)) : 0;
				int bVal = (x < b.widthX() && y < b.heightY()) ? Luminanz.colorToLuminance(b.getColor(x, y)) : 0;
				int newVal = aVal ^ bVal;
				result.setColor(x, y, new Color(newVal, newVal, newVal));
			}
		}
		return result;
	}

	public static Picture preShamirDecrypt(Picture a, Picture b) {
		Picture result = new Picture(Math.max(a.widthX(), b.widthX()), Math.max(a.heightY(), b.heightY()));
		for (int y = 0; y < result.heightY(); y++) {
			for (int x = 0; x < result.widthX(); x++) {
				int aVal = (x < a.widthX() && y < a.heightY()) ? Luminanz.colorToLuminance(a.getColor(x, y)) : 0;
				int bVal = (x < b.widthX() && y < b.heightY()) ? Luminanz.colorToLuminance(b.getColor(x, y)) : 0;
				int newVal = 255 - ((aVal + bVal) / 2);
				result.setColor(x, y, new Color(newVal, newVal, newVal));
			}
		}
		return result;
	}

}
