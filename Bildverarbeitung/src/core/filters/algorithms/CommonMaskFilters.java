package core.filters.algorithms;

import core.filters.Mask;
import core.filters.MaskFilter;

public class CommonMaskFilters {

	public static MaskFilter identity() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				0, 0, 0, // i = 0
				0, 1, 0, // i = 1
				0, 0, 0 // i = 2
		}));
	}

	public static MaskFilter gaussianBlur() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 2, 1, // i = 0
				2, 4, 2, // i = 1
				1, 2, 1 // i = 2
		}).normalizeTotal());
	}

	public static MaskFilter boxBlur() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 1, 1, // i = 0
				1, 1, 1, // i = 1
				1, 1, 1 // i = 2
		}).normalizeTotal());
	}

	public static MaskFilter sharpen() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				0, -1, 0, // i = 0
				-1, 5, -1, // i = 1
				0, -1, 0 // i = 2
		}));
	}

	public static MaskFilter edgeDetectionA() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 0, -1, // i = 0
				0, 0, 0, // i = 1
				-1, 0, 1 // i = 2
		}));
	}

	public static MaskFilter edgeDetectionB() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				0, -1, 0, // i = 0
				-1, 4, -1, // i = 1
				0, -1, 0 // i = 2
		}));
	}

	public static MaskFilter edgeDetectionC() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				-1, -1, -1, // i = 0
				-1, 8, -1, // i = 1
				-1, -1, -1 // i = 2
		}));
	}

	public static MaskFilter sobelX() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 0, -1, // i = 0
				2, 0, -2, // i = 1
				1, 0, -1 // i = 2
		}));
	}

	public static MaskFilter sobelY() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 2, 1, // i = 0
				0, 0, 0, // i = 1
				-1, -2, -1 // i = 2
		}));
	}

	public static MaskFilter scharrX() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				47, 0, -47, // i = 0
				162, 0, -162, // i = 1
				47, 0, -47 // i = 2
		}).multiplyScalar(1.0 / 64.0));
	}

	public static MaskFilter scharrY() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				47, 162, 47, // i = 0
				0, 0, 0, // i = 1
				-47, -162, -47 // i = 2
		}).multiplyScalar(1.0 / 64.0));
	}

//	public static MaskFilter scharrX() {
//		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
//				3, 0, -3, // i = 0
//				10, 0, -10, // i = 1
//				3, 0, -3 // i = 2
//		}));
//	}
//
//	public static MaskFilter scharrY() {
//		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
//				3, 10, 3, // i = 0
//				0, 0, 0, // i = 1
//				-3, -10, -3 // i = 2
//		}));
//	}

	public static MaskFilter prewittX() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 0, -1, // i = 0
				1, 0, -1, // i = 1
				1, 0, -1 // i = 2
		}));
	}

	public static MaskFilter prewittY() {
		return new MaskFilter(new Mask(new double[] { // Create matrix from 1D-array
				1, 1, 1, // i = 0
				0, 0, 0, // i = 1
				-1, -1, -1 // i = 2
		}));
	}
}
