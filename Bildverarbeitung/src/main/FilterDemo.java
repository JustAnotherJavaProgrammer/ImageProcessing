package main;

import core.filters.CombinedFilter;
import core.filters.Filter;
import core.filters.FilterRunner;
import core.filters.ImageViewMatrix;
import core.filters.algorithms.MaximumValueFilter;
import core.filters.algorithms.ScharrFilter;
import de.informatics4kids.Picture;

public class FilterDemo {

	public static void main(String[] args) {
		Filter fMax = MaximumValueFilter.getInstance();
		Filter fEdge = ScharrFilter.getInstance();
		Picture pic = new Picture("Carolina-Dog2.jpg");
		QuickView.showPicture(
				FilterRunner.applyFilter(pic, new CombinedFilter(fMax, fEdge, 0.75),
						ImageViewMatrix.EDGE_BEHAVIOUR_DUPLICATE));
	}

}
