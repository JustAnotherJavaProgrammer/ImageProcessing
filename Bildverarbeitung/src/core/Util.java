package core;

/**
 * Utility class providing static methods for working with arrays of
 * types {@code int[]} and {@code double[]}
 * 
 * @author JustAnotherJavaProgrammer
 */
public class Util {
	/**
	 * Calculates the minimum value an element in the array
	 * <p>
	 * If the array contains no elements ({@code length == 0}), this method will return {@code 0}.
	 * </p>
	 * @param nums An array of {@code int}s
	 * @return The smallest number in the given array
	 */
	public static int minOfMultiple(int[] nums) {
		if (nums.length == 0)
			return 0;
		int min = nums[0];
		for (int num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}

	/**
	 * Calculates the maximum value an element in the array
	 * <p>
	 * If the array contains no elements ({@code length == 0}), this method will return {@code 0}.
	 * </p>
	 * @param nums An array of {@code int}s
	 * @return The largest number in the given array
	 */
	public static int maxOfMultiple(int[] nums) {
		if (nums.length == 0)
			return 0;
		int max = nums[0];
		for (int num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}

	/**
	 * Calculates the minimum value an element in the array
	 * <p>
	 * If the array contains no elements ({@code length == 0}), this method will return {@code 0}.
	 * </p>
	 * @param nums An array of {@code double}s
	 * @return The smallest number in the given array
	 */
	public static double minOfMultiple(double[] nums) {
		if (nums.length == 0)
			return 0;
		double min = nums[0];
		for (double num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}

	/**
	 * Calculates the maximum value an element in the array
	 * <p>
	 * If the array contains no elements ({@code length == 0}), this method will return {@code 0}.
	 * </p>
	 * @param nums An array of {@code double}s
	 * @return The largest number in the given array
	 */
	public static double maxOfMultiple(double[] nums) {
		if (nums.length == 0)
			return 0;
		double max = nums[0];
		for (double num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}

	/**
	 * Private helper method to get the index of the index of the element with the smallest value in the array
	 * @param arr An array of {@code int}s
	 * @return The index of the smallest {@code int} in the array
	 */
	private static int minIndex(int[] arr) {
		int value = arr[0];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < value) {
				value = arr[i];
				index = i;
			}
		}
		return index;
	}

	// TODO: ADD DOCUMENTATION
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] sort(int[] arr) {
		arr = arr.clone();
		int[] sorted = new int[arr.length];
		for (int i = 0; i < sorted.length; i++) {
			int index = minIndex(arr);
			sorted[i] = arr[index];
			arr[index] = maxOfMultiple(arr);
		}
		return sorted;
	}

	private static int minIndex(double[] arr) {
		double value = arr[0];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < value) {
				value = arr[i];
				index = i;
			}
		}
		return index;
	}

	public static double[] sort(double[] arr) {
		arr = arr.clone();
		double[] sorted = new double[arr.length];
		for (int i = 0; i < sorted.length; i++) {
			int index = minIndex(arr);
			sorted[i] = arr[index];
			arr[index] = maxOfMultiple(arr);
		}
		return sorted;
	}

	public static int median(int[] arr) {
		arr = sort(arr);
		if (arr.length % 2 == 0)
			return arr[arr.length / 2 - 1] / 2 + arr[arr.length / 2] / 2;
		return arr[arr.length / 2];
	}

	public static double median(double[] arr) {
		arr = sort(arr);
		if (arr.length % 2 == 0)
			return arr[arr.length / 2 - 1] / 2 + arr[arr.length / 2] / 2;
		return arr[arr.length / 2];
	}

	public static int randomElement(int[] arr) {
		return arr[HideShamir.rand.nextInt(arr.length)];
	}

	public static double randomElement(double[] arr) {
		return arr[HideShamir.rand.nextInt(arr.length)];
	}
}
