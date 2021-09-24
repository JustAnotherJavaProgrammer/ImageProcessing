package core;

public class Util {
	public static int minOfMultiple(int[] nums) {
		if (nums.length == 0)
			return 0;
		int min = nums[0];
		for (int num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}

	public static int maxOfMultiple(int[] nums) {
		if (nums.length == 0)
			return 0;
		int max = nums[0];
		for (int num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}

	public static double minOfMultiple(double[] nums) {
		if (nums.length == 0)
			return 0;
		double min = nums[0];
		for (double num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}

	public static double maxOfMultiple(double[] nums) {
		if (nums.length == 0)
			return 0;
		double max = nums[0];
		for (double num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}

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
