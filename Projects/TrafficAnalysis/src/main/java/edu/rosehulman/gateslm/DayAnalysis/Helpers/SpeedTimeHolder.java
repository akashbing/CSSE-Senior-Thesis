package edu.rosehulman.gateslm.DayAnalysis.Helpers;

import java.util.Calendar;

public class SpeedTimeHolder {
	private float speed;
	private long time;

	public SpeedTimeHolder(float s, long t) {
		this.speed = s;
		this.time = t;
	}

	public int timeOfDaySeconds() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);

		return seconds + minutes * 60 + hours * 60 * 60;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}