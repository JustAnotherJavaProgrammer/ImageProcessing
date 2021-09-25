package core.filters.combinators;

import core.filters.Filter;
import core.filters.Matrix;

public class ConditionalCombinator implements Combinator {
	private Condition cond;

	public ConditionalCombinator(Condition cond) {
		this.cond = cond;
	}

	@Override
	public double combine(double filterOutputA, double filterOutputB, Matrix imageData, Filter fA, Filter fB) {
		return cond.apply(filterOutputA, filterOutputB, imageData, fA, fB) ? filterOutputA : filterOutputB;
	}

	public Condition getConditition() {
		return cond;
	}

	public void setCondition(Condition cond) {
		this.cond = cond;
	}

}
