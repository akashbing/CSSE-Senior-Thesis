package edu.rosehulman.gateslm.CongestionFinder.Helper;

public class SpeedIntervalContainer {

	private int startTime;
	private int endTime;
	private float speed;

	public SpeedIntervalContainer(int st, int et, float s) {
		this.startTime = st;
		this.endTime = et;
		this.speed = s;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
