package edu.rosehulman.gateslm.Models;

import java.util.ArrayList;
import java.util.List;

public class RegionModel {

	private String regionName;

	// Lowest longitude value
	private double west;

	// Highest longitude value
	private double east;

	// Lowest latitude value
	private double south;

	// Highest Latitude value
	private double north;
	private List<SegmentModel> segments;

	public RegionModel(String regionName, double d, double e, double f,
			double g) {
		this.regionName = regionName;
		this.west = d;
		this.east = e;
		this.south = f;
		this.north = g;
		this.segments = new ArrayList<>();
	}

	public boolean inRegion(SegmentModel sm) {
		// FIXME: Once implementation works, improve code structure
		if (this.west <= sm.getStartLong() && this.east >= sm.getStartLong()) {
			if (this.west <= sm.getEndLong() && this.east >= sm.getEndLong()) {
				if (this.south <= sm.getStartLat()
						&& this.north >= sm.getStartLat()) {
					if (this.south <= sm.getEndLat()
							&& this.north >= sm.getStartLat()) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public void addSegment(SegmentModel sm) {
		this.segments.add(sm);
	}

	public List<SegmentModel> getSegmentList() {
		return this.segments;
	}

	public String printSegmentList() {
		StringBuilder sb = new StringBuilder();
		for (SegmentModel sm : this.segments) {
			sb.append(sm.getSegmentId());
			sb.append(", ");
		}
		return this.regionName + ": " + sb.substring(0, sb.length() - 2);
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public double getWest() {
		return west;
	}

	public void setWest(double west) {
		this.west = west;
	}

	public double getEast() {
		return east;
	}

	public void setEast(double east) {
		this.east = east;
	}

	public double getSouth() {
		return south;
	}

	public void setSouth(double south) {
		this.south = south;
	}

	public double getNorth() {
		return north;
	}

	public void setNorth(double north) {
		this.north = north;
	}

	@Override
	public String toString() {
		return "RegionModel [regionName=" + regionName + ", west=" + west
				+ ", east=" + east + ", south=" + south + ", north=" + north
				+ "]";
	}

}
