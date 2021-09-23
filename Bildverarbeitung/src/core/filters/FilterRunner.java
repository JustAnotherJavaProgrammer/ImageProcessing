package core.filters;

import de.informatics4kids.Picture;

/**
 * Utility class to apply filters to pictures
 */
public class FilterRunner {
	public static Picture applyFilter(Picture pic, Filter f) {
		return applyFilter(pic, f, 3, 3);
	}
	
	public static Picture applyFilter(Picture pic, Filter f, int width, int height) {
		return applyFilter(pic, f, width, height, ImageViewMatrix.EDGE_BEHAVIOUR_THROW);
	}
	
	public static Picture applyFilter(Picture pic, Filter f, int edgeBehaviour) {
		return applyFilter(pic, f, 3, 3, edgeBehaviour);
	}
	
	public static Picture applyFilter(Picture pic, Filter f, int width, int height, int edgeBehaviour) {
		Picture result = new Picture(pic.widthX(), pic.heightY());
		ImageViewMatrix view = new ImageViewMatrix(pic, width, height, edgeBehaviour);
		for(int colorLayer = 0; colorLayer < 3; colorLayer++) {
			view.setColorLayer(colorLayer);
			for(int y = 0; y < pic.heightY(); y++) {
				for(int x = 0; x < pic.widthX(); x++) {
					view.setPosition(x, y);
					try {
						ImageViewMatrix.setColorLayerValue(result, x, y, colorLayer, (int) f.transform(view));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("Error at (" + x + ", " + y + ")");
					}
				}
			}
		}
		return result;
	}
}
