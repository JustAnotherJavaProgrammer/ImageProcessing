package core;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import de.informatics4kids.Picture;

public class Rotation {

	public static Picture rotateImage(Picture pic, double angle) {
		return Rotation.rotateImage(pic, angle, true);
	}

	public static Picture rotateImage(Picture pic, double angle, boolean resize) {
		Rectangle boundingBox = new Rectangle(0, 0, pic.widthX(), pic.heightY());
		Point offset = new Point(0, 0);
		if (resize) {
			boundingBox = Rotation.computeNewBoundingBox(boundingBox, angle);
			offset = Rotation.getOffset(boundingBox);
		}
//		else {
//			offset = Rotation.getOffset(Rotation.computeNewBoundingBox(boundingBox, angle));
//		}
		Picture result = new Picture(boundingBox.width, boundingBox.height);
		for (int y = 0; y < pic.heightY(); y++) {
			for (int x = 0; x < pic.widthX(); x++) {
				Point newPos = transformPoint(x, y, angle);
				newPos.x += offset.x;
				newPos.y += offset.y;
				if (newPos.x < 0 || newPos.x >= boundingBox.width || newPos.y < 0 || newPos.y >= boundingBox.height) {
					continue;
				}
				result.setColor(newPos.x, newPos.y, pic.getColor(x, y));
			}
		}
		return result;
	}

	public static Point getOffset(Rectangle boundingBox) {
		return new Point(-boundingBox.x, -boundingBox.y);
	}

	public static Rectangle computeNewBoundingBox(Rectangle orig, double angle) {
		Point topLeft = transformPoint(orig.x, orig.y, angle);
		Point topRight = transformPoint(orig.x + orig.width, orig.y, angle);
		Point bottomLeft = transformPoint(orig.x, orig.y + orig.height, angle);
		Point bottomRight = transformPoint(orig.x + orig.width, orig.y + orig.height, angle);
		int[] xCoordinates = new int[] { topLeft.x, topRight.x, bottomLeft.x, bottomRight.x };
		int[] yCoordinates = new int[] { topLeft.y, topRight.y, bottomLeft.y, bottomRight.y };
		Rectangle result = new Rectangle(Util.minOfMultiple(xCoordinates), Util.minOfMultiple(yCoordinates), 0, 0);
		result.width = Util.maxOfMultiple(xCoordinates) - result.x;
		result.height = Util.maxOfMultiple(yCoordinates) - result.y;
		return result;
	}

	public static Point transformPoint(int x, int y, double angle) {
		final Point2D result = transformPoint(new Point(x, y), angle);
		return new Point((int) result.getX(), (int) result.getY());
	}

	public static Point2D.Double transformPoint(Point2D orig, double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		return new Point2D.Double(cos * orig.getX() - sin * orig.getY(), sin * orig.getX() + cos * orig.getY());
	}

}
