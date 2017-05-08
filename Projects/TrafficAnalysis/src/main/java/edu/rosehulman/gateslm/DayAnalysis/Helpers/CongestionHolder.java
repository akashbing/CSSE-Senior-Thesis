package edu.rosehulman.gateslm.DayAnalysis.Helpers;

public class CongestionHolder {
	private int startTime;
	private int endTime;
	private int speedCounts;
	private float totalSpeed;

	public CongestionHolder(int startTime) {
		this.startTime = startTime;
		this.endTime = startTime;
		this.speedCounts = 0;
		this.totalSpeed = 0;
	}

	public float getAvgSpeed() {
		if (this.speedCounts == 0) {
			return 0;
		} else {
			return this.totalSpeed / (float) this.speedCounts;
		}
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getSpeedCounts() {
		return speedCounts;
	}

	public void setSpeedCounts(int speedCounts) {
		this.speedCounts = speedCounts;
	}

	public float getTotalSpeed() {
		return totalSpeed;
	}

	public void setTotalSpeed(float totalSpeed) {
		this.totalSpeed = totalSpeed;
	}

	public int getEndTime() {
		return endTime;
	}

	public void addSpeed(float speed) {
		this.speedCounts += 1;
		this.totalSpeed += speed;
	}

	public void setEndTime(int time) {
		this.endTime = time;
	}

	public int getTimeCongested() {
		return this.endTime - this.startTime;
	}
}