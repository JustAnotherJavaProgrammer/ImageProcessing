package core.filters;

public class CombinedFilter implements Filter {
	private Filter a;
	private Filter b;
	double weightA;
	double weightB;
	double min;
	double max;

	public CombinedFilter(Filter a, Filter b) {
		this(a, b, 0.5);
	}

	public CombinedFilter(Filter a, Filter b, double weightA) {
		this(a, b, weightA, 1.0 - weightA);
	}

	public CombinedFilter(Filter a, Filter b, double weightA, double weightB) {
		this(a, b, weightA, weightB, 0, 255);
	}

	public CombinedFilter(Filter a, Filter b, double weightA, double weightB, double min, double max) {
		this.a = a;
		this.b = b;
		this.weightA = weightA;
		this.weightB = weightB;
		this.min = min;
		this.max = max;
	}

	@Override
	public double transform(Matrix imageData) {
		double resA = clamp(a.transform(imageData));
		double resB = clamp(b.transform(imageData));
		return clamp(resA * weightA + resB * weightB);
	}

	private double clamp(double val) {
		if (val < min)
			return min;
		if (val > max)
			return max;
		return val;
	}

	public Filter getFilterA() {
		return a;
	}

	public Filter getFilterB() {
		return b;
	}

	public void setFilterA(Filter f) {
		a = f;
	}

	public void setFilterB(Filter f) {
		b = f;
	}

	public void setFilters(Filter a, Filter b) {
		setFilterA(a);
		setFilterB(b);
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

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public void setMax(double max) {
		this.max = max;
	}

}
