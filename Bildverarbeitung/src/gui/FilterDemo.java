package gui;

import core.filters.Filter;
import core.filters.FilterRunnerParallel;
import core.filters.ImageViewMatrix;
import core.filters.algorithms.MinimumValueFilter;
import core.filters.algorithms.ScharrFilter;
import core.filters.combinators.CombinedFilter;
import core.filters.combinators.CommonCombinators;
import de.informatics4kids.Picture;

public class FilterDemo {

	public static void main(String[] args) {
		Filter fMax = MinimumValueFilter.getInstance();
		Filter fEdge = ScharrFilter.getInstance();
		Picture pic = new Picture("/home/student/Downloads/Carolina_dog_Ball_.jpg");
		QuickView.showPicture(FilterRunnerParallel.applyFilter(pic, new CombinedFilter(fMax, fEdge, CommonCombinators.max()),
				ImageViewMatrix.EDGE_BEHAVIOUR_DUPLICATE));
	}

}
