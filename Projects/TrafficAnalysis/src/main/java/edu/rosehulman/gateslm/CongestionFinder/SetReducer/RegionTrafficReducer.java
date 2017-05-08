package edu.rosehulman.gateslm.CongestionFinder.SetReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import edu.rosehulman.gateslm.CongestionFinder.Helper.SpeedIntervalContainer;
import edu.rosehulman.gateslm.CongestionFinder.Set.RegionTrafficSet;

public class RegionTrafficReducer
		extends Reducer<Text, RegionTrafficSet, Text, Text> {

	public final int TOTAL_SECONDS_IN_DAY = 60 * 60 * 24;

	@Override
	protected void reduce(Text key, Iterable<RegionTrafficSet> values,
			Context context) throws IOException, InterruptedException {
		List<SpeedIntervalContainer> highCongestionLists = new ArrayList<>();

		int totalDays = 0;

		for (RegionTrafficSet value : values) {
			totalDays++;
			// Save the values that have some sort of time in them and the start
			// time does not equal the end time
			if (value.getStartHeavyTime().get() != -1
					&& value.getStartHeavyTime().get() != value
							.getEndHeavyTime().get()) {
				highCongestionLists.add(new SpeedIntervalContainer(
						value.getStartHeavyTime().get(),
						value.getEndHeavyTime().get(),
						value.getAvgSpeed().get()));
			}
		}

		// Percentage of high congestion found
		double percentageFound = (double) highCongestionLists.size()
				/ (double) totalDays;

		if (percentageFound < .75) {
			context.write(key, new Text("Ignore"));
			return;
		}

		if (highCongestionLists.isEmpty()) {
			context.write(key, new Text("Ignore"));
			return;
		}
		int maxIts = highCongestionLists.size();
		int count = 0;
		int numberOfElements = 1;
		SpeedIntervalContainer maxSIC = highCongestionLists.remove(0);
		while (!highCongestionLists.isEmpty() && count < maxIts) {
			SpeedIntervalContainer current = highCongestionLists.remove(0);
			boolean connected = false;
			if (current.getStartTime() <= maxSIC.getStartTime()
					&& current.getEndTime() >= maxSIC.getStartTime()) {
				maxSIC.setStartTime(current.getStartTime());
				connected = true;
			}
			if (current.getEndTime() >= maxSIC.getEndTime()
					&& current.getStartTime() <= maxSIC.getEndTime()) {
				maxSIC.setEndTime(current.getEndTime());
				connected = true;
			}
			if (!connected) {
				highCongestionLists.add(current);
			} else {
				maxSIC.setSpeed(maxSIC.getSpeed() + current.getSpeed());
				numberOfElements++;
			}
			count++;
		}
		maxSIC.setSpeed(maxSIC.getSpeed() / numberOfElements);

		int interval = maxSIC.getEndTime() - maxSIC.getStartTime();
		double intDay = (double) interval / (double) TOTAL_SECONDS_IN_DAY;
		if (intDay > .25 && maxSIC.getSpeed() < 15) {
			context.write(key, new Text("Use " + intDay));
		} else {
			context.write(key, new Text("Ignore"));
		}
	}

}
