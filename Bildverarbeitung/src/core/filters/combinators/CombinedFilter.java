package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public class CombinedFilter implements Filter {
	protected Filter a;
	protected Filter b;
	protected Combinator comb;
	protected Clamp clamp;

	public CombinedFilter(Filter a, Filter b, Combinator comb) {
		this(a, b, comb, Clamp.getDefault());
	}

	public CombinedFilter(Filter a, Filter b, Combinator comb, Clamp clamp) {
		this.a = a;
		this.b = b;
		this.comb = comb;
		this.clamp = clamp;
	}

	@Override
	public double transform(Matrix imageData) {
		double resA = clamp.clamp(a.transform(imageData));
		double resB = clamp.clamp(b.transform(imageData));
		return clamp.clamp(comb.combine(resA, resB, imageData, a, b));
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

	public Combinator getCombinator() {
		return comb;
	}

	public void setCombinator(Combinator comb) {
		this.comb = comb;
	}

	public Clamp getClamp() {
		return clamp;
	}

	public void setClamp(Clamp clamp) {
		this.clamp = clamp;
	}

}
