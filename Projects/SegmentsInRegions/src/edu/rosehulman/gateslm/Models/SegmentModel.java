package edu.rosehulman.gateslm.Models;

public class SegmentModel {

	private String segmentId;
	private double startLong;
	private double startLat;
	private double endLong;
	private double endLat;

	public SegmentModel(String segmentId, double startLong, double startLat,
			double endLong, double endLat) {
		this.segmentId = segmentId;
		this.startLong = startLong;
		this.startLat = startLat;
		this.endLong = endLong;
		this.endLat = endLat;
	}

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public double getStartLong() {
		return startLong;
	}

	public void setStartLong(double startLong) {
		this.startLong = startLong;
	}

	public double getStartLat() {
		return startLat;
	}

	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}

	public double getEndLong() {
		return endLong;
	}

	public void setEndLong(double endLong) {
		this.endLong = endLong;
	}

	public double getEndLat() {
		return endLat;
	}

	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}

	@Override
	public String toString() {
		return "SegmentModel [segmentId=" + segmentId + ", startLong="
				+ startLong + ", startLat=" + startLat + ", endLong=" + endLong
				+ ", endLat=" + endLat + "]";
	}

}
