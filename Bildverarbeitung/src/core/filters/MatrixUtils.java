package core.filters;

public class MatrixUtils {
	public static double[] flatten(double[][] arr) {
		int len = 0;
		for (double[] line : arr) {
			len += line.length;
		}
		double[] result = new double[len];
		int index = 0;
		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {
				result[index] = arr[x][y];
				index++;
			}
		}
		return result;
	}

	public static double[][] wrap(double[] arr, int width) {
		if (arr.length % width != 0)
			throw new IllegalArgumentException("The number of values must be divisible by the width of the matrix!");
		double[][] result = new double[width][arr.length / width];
		for (int i = 0; i < arr.length; i++) {
			result[i / width][i % width] = arr[i];
		}
		return result;
	}

	public static double[][] copyArr(double[][] arr) {
		double[][] result = new double[arr.length][];
		for(int x = 0; x < arr.length; x++) {
			result[x] = new double[arr[x].length];
			for(int y = 0; y < arr[x].length; y++) {
				result[x][y] = arr[x][y];
			}
		}
		return result;
	}
	
	public static void sameLengthCheck(double[][] arr) {
		for (double[] line : arr) {
			if (line.length != arr[0].length)
				throw new IllegalArgumentException("All lines of the matrix must be equally long!");
		}
	}
	
	public static double[][] swapDimensions(double[][] arr) {
		sameLengthCheck(arr);
		double[][] vals = new double[arr[0].length][arr.length];
		for (int x = 0; x < vals.length; x++) {
			for (int y = 0; y < vals[x].length; y++) {
				vals[x][y] = arr[y][x];
			}
		}
		return vals;
	}
}
