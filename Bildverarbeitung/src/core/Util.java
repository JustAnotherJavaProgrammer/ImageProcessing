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
}
