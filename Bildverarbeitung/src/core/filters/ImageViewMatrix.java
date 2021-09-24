package core.filters;

import java.awt.Color;
import java.awt.Point;

import de.informatics4kids.Picture;

public class ImageViewMatrix extends Matrix {
	public static final int EDGE_BEHAVIOUR_THROW = 0;
	public static final int EDGE_BEHAVIOUR_WRAP = 1;
	public static final int EDGE_BEHAVIOUR_DUPLICATE = 2;
	public static final int EDGE_BEHAVIOUR_BLACK = 3;

	public static final int COLOR_LAYER_RED = 0;
	public static final int COLOR_LAYER_GREEN = 1;
	public static final int COLOR_LAYER_BLUE = 2;

	protected int edgeBehaviour = EDGE_BEHAVIOUR_THROW;
	protected Point position = new Point(0, 0);
	protected final int offX;
	protected final int offY;
	protected final Picture pic;
	protected int colorLayer = COLOR_LAYER_RED;

	public ImageViewMatrix(Picture pic) {
		this(pic, 3, 3);
	}

	public ImageViewMatrix(Picture pic, int width, int height) {
		this(pic, width, height, EDGE_BEHAVIOUR_THROW);
	}

	public ImageViewMatrix(Picture pic, int edgeBehaviour) {
		this(pic, 3, 3, edgeBehaviour);
	}

	public ImageViewMatrix(Picture pic, int width, int height, int edgeBehaviour) {
		super(width, height);
		this.pic = pic;
		offX = -width / 2;
		offY = -height / 2;
		this.edgeBehaviour = edgeBehaviour;
	}

	public void setPosition(int x, int y) {
		if (coordinatesOutOfBounds(x, y))
			throw new IllegalArgumentException("Coordinates (" + x + ", " + y + ") are out of bounds!");
		position.x = x;
		position.y = y;
	}

	public void setPosition(Point p) {
		setPosition(p.x, p.y);
	}

	public Point getPosition() {
		return new Point(position);
	}

	public void setEdgeBehaviour(int edgeBehaviour) {
		if (edgeBehaviour > 3)
			edgeBehaviour = EDGE_BEHAVIOUR_THROW;
		this.edgeBehaviour = edgeBehaviour;
	}

	public int getEdgeBehaviour() {
		return edgeBehaviour;
	}

	public Picture getPicture() {
		return pic;
	}

	public int getXOffset() {
		return offX;
	}

	public int getYOffset() {
		return offY;
	}

	public int getColorLayer() {
		return colorLayer;
	}

	public void setColorLayer(int colorLayer) {
		this.colorLayer = colorLayer % 3;
	}

	@Override
	public double getValue(int i, int j) {
		int x = position.x + offX + j;
		int y = position.y + offY + i;
		if (coordinatesOutOfBounds(x, y)) {
			if (edgeBehaviour == EDGE_BEHAVIOUR_THROW)
				throw new ArrayIndexOutOfBoundsException("Coordinate out of bounds!"); // Mimicking fsglib behaviour
			if (edgeBehaviour == EDGE_BEHAVIOUR_BLACK)
				return 0;
			if (edgeBehaviour == EDGE_BEHAVIOUR_DUPLICATE) {
				if (x < 0)
					x = 0;
				else if (x >= pic.widthX())
					x = pic.widthX() - 1;
				if (y < 0)
					y = 0;
				else if (y >= pic.heightY())
					y = pic.heightY() - 1;
			} else { // edgeBehaviour == EDGE_BEHAVIOUR_WRAP
				while (x < 0)
					x += pic.widthX();
				while (x >= pic.widthX())
					x -= pic.widthX();
				while (y < 0)
					y += pic.heightY();
				while (y >= pic.heightY())
					y -= pic.heightY();
			}
		}
		return getColorValueAtAbsolutePosition(x, y);
	}

	protected boolean coordinatesOutOfBounds(int x, int y) {
		return x < 0 || x >= pic.widthX() || y < 0 || y >= pic.heightY();
	}

	protected int getColorValueAtAbsolutePosition(int x, int y) {
		Color c = pic.getColor(x, y);
		switch (colorLayer) {
		case COLOR_LAYER_RED:
			return c.getRed();
		case COLOR_LAYER_GREEN:
			return c.getGreen();
		case COLOR_LAYER_BLUE:
			return c.getBlue();
		default:
			throw new IllegalStateException("Unsupported color layer: " + colorLayer);
		}
	}

	@Override
	public double[][] toArray() {
		double[][] result = new double[height()][width()];
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				result[i][j] = getValue(i, j);
			}
		}
		return result;
	}

	@Override
	public void setValue(int i, int j, double val) {
		// ImageViewMatrices are read-only!
	}

	public static void setColorLayerValue(Picture pic, int x, int y, int colorLayer, int value) {
		Color c = pic.getColor(x, y);
		if (value > 255)
			value = 255;
		else if (value < 0)
			value = 0;
		pic.setColor(x, y,
				new Color(colorLayer == COLOR_LAYER_RED ? value : c.getRed(),
						colorLayer == COLOR_LAYER_GREEN ? value : c.getGreen(),
						colorLayer == COLOR_LAYER_BLUE ? value : c.getBlue()));
	}

}
