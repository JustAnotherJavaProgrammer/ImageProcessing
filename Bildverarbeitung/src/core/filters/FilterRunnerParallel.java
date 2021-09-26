package core.filters;

import java.awt.Color;
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
		int[][][] results = new int[pic.heightY()][pic.widthX()][3];
		IntStream.range(0, 3).parallel().forEach(colorLayer -> {
			IntStream.range(0, results.length).parallel().forEach(y -> {
				ImageViewMatrix localCopy = view.quickClone();
				localCopy.setColorLayer(colorLayer);
				// Not using a parallel version of this part actually makes it faster
				for (int x = 0; x < results[y].length; x++) {
					localCopy.setPosition(x, y);
					try {
						results[y][x][colorLayer] = (int) filters[colorLayer % filters.length].transform(localCopy);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("Error at (" + x + ", " + y + ")");
					}
				}
				;
			});
		});
		IntStream.range(0, results.length).parallel().forEach(y -> {
			IntStream.range(0, results[y].length).forEach(x -> {
				int[] res = results[y][x];
				result.setColor(x, y, new Color(res[0], res[1], res[2]));
			});
		});
		return result;
	}

//	public static Picture applyFilter(Picture pic, Filter[] filters, int width, int height, int edgeBehaviour) {
//		Picture result = new Picture(pic.widthX(), pic.heightY());
//		ImageViewMatrix view = new ImageViewMatrix(pic, width, height, edgeBehaviour);
//		IntStream.range(0, pic.heightY()).parallel().forEach(y -> {
//			IntStream.range(0, pic.widthX()).forEach(x -> {
//				ImageViewMatrix localCopy = view.quickClone();
//				localCopy.setPosition(x, y);
////				for (int colorLayer = 0; colorLayer < 3; colorLayer++) {
////					localCopy.setColorLayer(colorLayer);
////					try {
////						res[colorLayer] = (int) filters[colorLayer % filters.length].transform(localCopy);
////					} catch (ArrayIndexOutOfBoundsException e) {
////						System.err.println("Error at (" + x + ", " + y + ")");
////					}
////				}
////				localCopy.setColorLayer(0);
////				int r = 0;
////				try {
////					r = (int) filters[0 % filters.length].transform(localCopy);
////				} catch (ArrayIndexOutOfBoundsException e) {
////					System.err.println("Error at (" + x + ", " + y + ")");
////				}
////				localCopy.setColorLayer(1);
////				int g = 0;
////				try {
////					g = (int) filters[1 % filters.length].transform(localCopy);
////				} catch (ArrayIndexOutOfBoundsException e) {
////					System.err.println("Error at (" + x + ", " + y + ")");
////				}
////				localCopy.setColorLayer(2);
////				int b = 0;
////				try {
////					b = (int) filters[2 % filters.length].transform(localCopy);
////				} catch (ArrayIndexOutOfBoundsException e) {
////					System.err.println("Error at (" + x + ", " + y + ")");
////				}
//				result.setColor(x, y, new Color((int)transform(0, filters[0], localCopy), (int)transform(1, filters[1%filters.length], localCopy), (int)transform(2, filters[2%filters.length], localCopy)));
//			});
//		});
//		return result;
//	}
//	
//	private static double transform(int colorLayer, Filter f, ImageViewMatrix input) {
//		input.setColorLayer(colorLayer);
//		return f.transform(input);
//	}
}
