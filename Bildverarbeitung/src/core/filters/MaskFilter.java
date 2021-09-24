package core.filters;

public class MaskFilter implements Filter {
	Mask mask;

	public MaskFilter() {
		mask = new Mask();
	}

	public MaskFilter(Mask mask) {
		setMask(mask);
	}
	
	public Mask getMask() {
		return mask;
	}

	public void setMask(Mask mask) {
		this.mask = mask;
	}

	@Override
	public double transform(Matrix imageData) {
		if (imageData.width() != 3 || imageData.height() != 3)
			throw new IllegalArgumentException("The image data must be a matrix of type 3x3!");
		return imageData.fold(mask);
	}

}
