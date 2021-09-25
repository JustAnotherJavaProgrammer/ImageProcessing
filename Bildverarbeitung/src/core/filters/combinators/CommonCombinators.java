package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public class CommonCombinators {
	private static Combinator min;

	public static Combinator min() {
		if (min == null)
			min = new Combinator() {

				@Override
				public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
					return Math.min(filterOutputA, filterOutputB);
				}
			};
		return min;
	}

	private static Combinator max;

	public static Combinator max() {
		if (max == null)
			max = new Combinator() {

				@Override
				public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
					return Math.max(filterOutputA, filterOutputB);
				}
			};
		return max;
	}

	private static Combinator sum;

	public static Combinator sum() {
		if (sum == null)
			sum = new Combinator() {

				@Override
				public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
					return filterOutputA + filterOutputB;
				}
			};
		return sum;
	}

	private static Combinator diff;

	public static Combinator diff() {
		if (diff == null)
			diff = new Combinator() {

				@Override
				public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
					return filterOutputA - filterOutputB;
				}
			};
		return diff;
	}

	private static Combinator diffAbsolute;

	public static Combinator diffAbsolute() {
		if (diffAbsolute == null)
			diffAbsolute = new Combinator() {

				@Override
				public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
					return Math.abs(filterOutputA - filterOutputB);
				}
			};
		return diffAbsolute;
	}
}
