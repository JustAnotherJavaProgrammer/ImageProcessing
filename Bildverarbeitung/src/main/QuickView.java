package main;
import de.informatics4kids.Picture;
import de.informatics4kids.PictureViewer;

public class QuickView {
	public static void showPicture(Picture pic) {
		PictureViewer v = new PictureViewer(pic.getPicture());
		v.show();
	}
}
