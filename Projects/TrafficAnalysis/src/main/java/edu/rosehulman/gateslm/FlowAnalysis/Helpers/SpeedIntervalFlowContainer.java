package edu.rosehulman.gateslm.FlowAnalysis.Helpers;
public class SpeedIntervalFlowContainer {

	private int startTime;
	private int endTime;
	private float speed;
	private String theme;
	private int rating;

	public SpeedIntervalFlowContainer(int st, int et, float s, String th, int r) {
		this.startTime = st;
		this.endTime = et;
		this.speed = s;
		this.theme = th;
		this.rating = r;
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}