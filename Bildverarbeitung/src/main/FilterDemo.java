package main;

import core.filters.Filter;
import core.filters.FilterRunner;
import core.filters.Mask;
import core.filters.MaskFilter;
import de.informatics4kids.Picture;

public class FilterDemo {

	public static void main(String[] args) {
		Mask mask = new Mask(new double[] { // Create matrix from 1D-array
				1, 2, 1, // i = 0
				2, 4, 2, // i = 1
				1, 2, 1 // i = 2
		}).multiplyScalar(0.0625);
		Filter f = new MaskFilter(mask);
		QuickView.showPicture(FilterRunner.applyFilter(new Picture("Panorama-Schulhof-mit-Text.png"), f));
	}

}
