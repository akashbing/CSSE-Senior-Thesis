package edu.rosehulman.gateslm.runner;

import java.util.List;

import edu.rosehulman.gateslm.Models.RegionModel;
import edu.rosehulman.gateslm.Models.SegmentModel;

public class Runner {

	public static void main(String[] args) {
		List<RegionModel> regionList = ListGenerator.createRegionList();
		List<SegmentModel> segmentList = ListGenerator.createSegmentList();
		
		RegionModel crossed = new RegionModel("Cross", 0, 0, 0, 0);
		for (SegmentModel sm : segmentList) {
			boolean found = false;
			for (RegionModel rm : regionList) {
				if (rm.inRegion(sm)) {
					rm.addSegment(sm);
					found = true;
					break;
				}
			}
			if (!found) {
				crossed.addSegment(sm);
			}
		}

		for (RegionModel rm : regionList) {
			System.out.println(rm.printSegmentList());
		}
		System.out.println(crossed.printSegmentList());
	}

}
