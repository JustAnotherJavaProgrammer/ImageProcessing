package core.filters.algorithms.bonus;

import core.filters.Filter;
import core.filters.Matrix;
import core.filters.MatrixUtils;

public class ConwaysGameOfLife implements Filter {

	private static ConwaysGameOfLife instance;

	public static ConwaysGameOfLife getInstance() {
		if (instance == null)
			instance = new ConwaysGameOfLife();
		return instance;
	}

	private ConwaysGameOfLife() {
	}

	@Override
	public double transform(Matrix imageData) {
		if (imageData.width() != 3 || imageData.height() != 3)
			throw new ArrayIndexOutOfBoundsException("The \"Game of Life\" filter expects 3x3 matrices as input data!");
		boolean isAlive = isAlive(imageData, 128);
		int livingNeighbours = countLivingCells(imageData, 128) - (isAlive ? 1 : 0);
		if (!isAlive && livingNeighbours == 3)
			return 255;
		if (isAlive) {
			if (livingNeighbours < 2)
				return 0;
			if (livingNeighbours > 3)
				return 0;
		}
		return imageData.getValue(1, 1);
	}

	private int countLivingCells(Matrix imageData, double threshold) {
		int counter = 0;
		for (double value : MatrixUtils.flatten(imageData.toArray())) {
			if (value >= threshold)
				counter++;
		}
		return counter;
	}

	private boolean isAlive(Matrix imageData, double threshold) {
		return imageData.getValue(1, 1) >= threshold;
	}

}
