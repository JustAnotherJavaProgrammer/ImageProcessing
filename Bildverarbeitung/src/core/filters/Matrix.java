package core.filters;

public class Matrix {
	protected final double[][] arr;

	public int width() {
		return arr[0].length;
	}

	public int height() {
		return arr.length;
	}

	public int[] type() {
		return new int[] { width(), height() };
	}

	public Matrix(int width, int height) {
		this(new double[width * height], width);
	}

	public Matrix(double[] values, int width) {
		this(MatrixUtils.swapDimensions(MatrixUtils.wrap(values, width)));
	}

	public Matrix(double[][] values) {
		if (values.length != 0)
			MatrixUtils.sameLengthCheck(values);
		arr = values;
	}

	private void indexCheck(int i, int j) {
		if (j < 0 || j >= width() || i < 0 || i >= height())
			throw new ArrayIndexOutOfBoundsException((j < 0 || j >= width()) ? j : i);
	}

	public double getValue(int i, int j) {
		indexCheck(i, j);
		return arr[i][j];
	}

	public double getAtPosition(int x, int y) {
		return getValue(y, x);
	}

	public void setValue(int i, int j, double val) {
		indexCheck(i, j);
		arr[i][j] = val;
	}

	public void setValueAtPosition(int x, int y, double val) {
		setValue(y, x, val);
	}

	public Matrix multiplyScalar(double factor) {
		double[] vals = MatrixUtils.flatten(toArray());
		for (int i = 0; i < vals.length; i++) {
			vals[i] *= factor;
		}
		return new Matrix(MatrixUtils.wrap(vals, height()));
	}

	public Matrix multiply(Matrix m) {
		if (this.height() != m.width())
			throw new IllegalArgumentException(
					"The matrices cannot be multiplied because the operation is undefined for matrices of types "
							+ this.width() + "x" + this.height() + " and " + m.width() + "x" + m.height() + "!");
		int k = this.height();
		double[][] res = new double[this.width()][m.height()];
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				int val = 0;
				for (int index = 0; index < k; index++) {
					val += getValue(i, index) * m.getValue(index, j);
				}
				res[i][j] = val;
			}
		}
		return new Matrix(res);
	}

	public double fold(Matrix m) {
		if (this.width() != m.width() || this.height() != m.height())
			throw new IllegalArgumentException("The two matrices must be of the same type!");
		double res = 0;
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				res += this.getValue(i, j) * m.getValue(i, j);
			}
		}
		return res;
	}

	/**
	 * Calculates the sum of all elements in the matrix
	 * @return The total value of all elements in the matrix
	 */
	public double total() {
		double total = 0;
		for (int i = 0; i < height(); i++) {
			for (int j = 0; j < width(); j++) {
				total += getValue(i,j);
			}
		}
		return total;
	}

	/**
	 * <b>WARNING: </b>This method does <b>not</b> perform the usual normalization
	 * of a matrix, instead it scales the matrix, so that the total of the resulting
	 * matrix becomes 1.
	 * 
	 * @return A matrix with a {@link #total()} of 1
	 */
	public Matrix normalizeTotal() {
		return this.multiplyScalar(this.total());
	}

	public Matrix transpose() {
		double[][] vals = new double[height()][width()];
		for (int x = 0; x < vals.length; x++) {
			for (int y = 0; y < vals[x].length; y++) {
				vals[x][y] = getValue(y,x);
			}
		}
		return new Matrix(vals);
	}
	
	public double[][] toArray() {
		return MatrixUtils.copyArr(arr);
	}

	public Matrix copy() {
		return new Matrix(MatrixUtils.copyArr(toArray()));
	}

	@Override
	public String toString() {
		if (height() == 0)
			return "[ ]";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < height(); i++) {
			if (i == 0)
				builder.append(height() == 1 ? "[" : "\u231c");
			else if (i == height() - 1)
				builder.append("\u231e");
			else
				builder.append("|");
			builder.append(" ");
			for (int j = 0; j < width(); j++) {
				builder.append(getValue(i, j));
				if (j != width() - 1)
					builder.append(",");
				builder.append(" ");
			}
			if (i == 0)
				builder.append(height() == 1 ? "]" : "\u231d");
			else if (i == height() - 1)
				builder.append("\u231f");
			else
				builder.append("|");
			if (i != height() - 1)
				builder.append(System.lineSeparator());
		}

		return builder.toString();
	}
}
