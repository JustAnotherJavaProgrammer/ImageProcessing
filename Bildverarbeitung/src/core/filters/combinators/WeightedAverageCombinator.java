package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public class WeightedAverageCombinator implements Combinator {
	double weightA;
	double weightB;

	public WeightedAverageCombinator(double weightA) {
		this(weightA, 1.0 - weightA);
	}

	public WeightedAverageCombinator(double weightA, double weightB) {
		this.weightA = weightA;
		this.weightB = weightB;
	}

	public static WeightedAverageCombinator getDefault() {
		return new WeightedAverageCombinator(0.5);
	}

	@Override
	public double combine(double filterOutputA, double filterOutputB, Matrix input, Filter fA, Filter fB) {
		return filterOutputA * weightA + filterOutputB * weightB;
	}

	public double getWeightA() {
		return weightA;
	}

	public double getWeightB() {
		return weightB;
	}

	public double[] getWeights() {
		return new double[] { weightA, weightB };
	}

	public void setWeightA(double weightA) {
		this.weightA = weightA;
	}

	public void setWeightB(double weightB) {
		this.weightB = weightB;
	}

	public void setWeights(double weightA, double weightB) {
		this.weightA = weightA;
		this.weightB = weightB;
	}
}
