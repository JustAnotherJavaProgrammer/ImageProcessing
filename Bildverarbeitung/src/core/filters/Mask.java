package core.filters;

public class Mask extends Matrix {

	public Mask() {
		super(3,3);
		sizeCheck(); // pro forma size check
	}
	
	public Mask(int width, int height) {
		super(width, height);
		sizeCheck();
	}

	public Mask(double[] values) {
		super(values, 3);
		sizeCheck();
	}

	public Mask(double[][] values) {
		super(values);
		sizeCheck();
	}

	private void sizeCheck() {
		if (width() != 3 || height() != 3)
			throw new IllegalArgumentException("Masks must be matrices of type 3x3!");
	}

	public static Mask from(Matrix m) {
		return new Mask(m.toArray());
	}

	@Override
	public Mask multiplyScalar(double factor) {
		return Mask.from(super.multiplyScalar(factor));
	}

	public Mask multiplyMasks(Mask m) {
		return Mask.from(super.multiply(m));
	}

	@Override
	public Mask normalizeTotal() {
		return Mask.from(super.normalizeTotal());
	}

	@Override
	public Mask transpose() {
		return Mask.from(super.transpose());
	}

	@Override
	public Mask copy() {
		return Mask.from(super.copy());
	}
}
