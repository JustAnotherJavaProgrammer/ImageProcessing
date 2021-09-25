package core.filters;

import java.util.stream.IntStream;

import de.informatics4kids.Picture;

/**
 * Parallel version of {@link FilterRunner}<br>
 */
public class FilterRunnerParallel extends FilterRunner {
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
		return applyFilter(pic, new Filter[] { f }, width, height, edgeBehaviour);
	}

	public static Picture applyFilter(Picture pic, Filter[] filters) {
		return applyFilter(pic, filters, 3, 3);
	}

	public static Picture applyFilter(Picture pic, Filter[] filters, int width, int height) {
		return applyFilter(pic, filters, width, height, ImageViewMatrix.EDGE_BEHAVIOUR_THROW);
	}

	public static Picture applyFilter(Picture pic, Filter[] filters, int edgeBehaviour) {
		return applyFilter(pic, filters, 3, 3, edgeBehaviour);
	}

	public static Picture applyFilter(Picture pic, Filter[] filters, int width, int height, int edgeBehaviour) {
		Picture result = new Picture(pic.widthX(), pic.heightY());
		ImageViewMatrix view = new ImageViewMatrix(pic, width, height, edgeBehaviour);
		IntStream.range(0, 3).parallel().forEach(colorLayer -> {
			IntStream.range(0, pic.heightY()).parallel().forEach(y -> {
				ImageViewMatrix localCopy = view.quickClone();
				localCopy.setColorLayer(colorLayer);
				// Not using a parallel version of this part actually makes it faster
				for (int x = 0; x < pic.widthX(); x++) {
					localCopy.setPosition(x, y);
					try {
						ImageViewMatrix.setColorLayerValue(result, x, y, colorLayer,
								(int) filters[colorLayer % filters.length].transform(localCopy));
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("Error at (" + x + ", " + y + ")");
					}
				}
				;
			});
		});
		return result;
	}

//	public static Picture applyFilter(Picture pic, Filter[] filters, int width, int height, int edgeBehaviour) {
//		Picture result = new Picture(pic.widthX(), pic.heightY());
//		ImageViewMatrix view = new ImageViewMatrix(pic, width, height, edgeBehaviour);
//		IntStream.range(0, 3).parallel().forEach(colorLayer -> {
//			IntStream.range(0, pic.heightY()).parallel().forEach(y -> {
//				IntStream.range(0, pic.widthX()).parallel().forEach(x -> {
//					ImageViewMatrix localCopy = view.quickClone();
//					localCopy.setColorLayer(colorLayer);
//					localCopy.setPosition(x, y);
//					try {
//						ImageViewMatrix.setColorLayerValue(result, x, y, colorLayer,
//								(int) filters[colorLayer % filters.length].transform(localCopy));
//					} catch (ArrayIndexOutOfBoundsException e) {
//						System.err.println("Error at (" + x + ", " + y + ")");
//					}
//				});
//			});
//		});
//		return result;
//	}
}
