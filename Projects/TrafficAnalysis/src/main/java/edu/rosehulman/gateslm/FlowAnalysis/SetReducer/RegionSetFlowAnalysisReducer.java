package edu.rosehulman.gateslm.FlowAnalysis.SetReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import edu.rosehulman.gateslm.FlowAnalysis.Helpers.SpeedIntervalFlowContainer;
import edu.rosehulman.gateslm.FlowAnalysis.Sets.RegionSetFlowAnalysis;

public class RegionSetFlowAnalysisReducer
		extends Reducer<Text, RegionSetFlowAnalysis, Text, Text> {

	public final int TOTAL_SECONDS_IN_DAY = 60 * 60 * 24;

	@Override
	protected void reduce(Text key, Iterable<RegionSetFlowAnalysis> values,
			Context context) throws IOException, InterruptedException {
		List<SpeedIntervalFlowContainer> highCongestionLists = new ArrayList<>();
		Map<String, Integer> themeTotals = new TreeMap<>();
		Map<String, Integer> themeMaxRating = new HashMap<>();

		int totalDays = 0;

		for (RegionSetFlowAnalysis value : values) {
			totalDays++;
			// Save the values that have some sort of time in them and the start
			// time does not equal the end time
			if (value.getStartHeavyTime().get() != -1
					&& value.getStartHeavyTime().get() != value
							.getEndHeavyTime().get()) {
				highCongestionLists.add(new SpeedIntervalFlowContainer(
						value.getStartHeavyTime().get(),
						value.getEndHeavyTime().get(),
						value.getAvgSpeed().get(), value.getTheme().toString(),
						value.getRating().get()));
				String theme = value.getTheme().toString();
				if (themeTotals.containsKey(theme)) {
					themeTotals.put(theme, themeTotals.get(theme) + 1);
				} else {
					themeTotals.put(theme, 1);
				}
				if (themeMaxRating.containsKey(theme)) {
					themeMaxRating.put(theme,
							Math.max(themeMaxRating.get(theme),
									value.getRating().get()));
				} else {
					themeMaxRating.put(theme, value.getRating().get());
				}
			}
		}

		// Percentage of high congestion found
		double percentageFound = (double) highCongestionLists.size()
				/ (double) totalDays;

		if (percentageFound < .75) {
			context.write(key, new Text("Ignore\t-1\t-1"));
			return;
		}

		if (highCongestionLists.isEmpty()) {
			context.write(key, new Text("Ignore\t-2\t-2"));
			return;
		}
		int maxIts = highCongestionLists.size();
		int count = 0;
		int numberOfElements = 1;
		SpeedIntervalFlowContainer maxSIC = highCongestionLists.remove(0);
		while (!highCongestionLists.isEmpty() && count < maxIts) {
			SpeedIntervalFlowContainer current = highCongestionLists.remove(0);
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
			List<Map.Entry<String, Integer>> themeTotalsList = new ArrayList<>(
					themeTotals.entrySet());

			// Puts in the order of highest count for theme to lowest count for
			// theme
			Collections.sort(themeTotalsList,
					new Comparator<Map.Entry<String, Integer>>() {

						@Override
						public int compare(Entry<String, Integer> o1,
								Entry<String, Integer> o2) {
							return -o1.getValue().compareTo(o2.getValue());
						}
					});

			// FIXME: Need to go through and find the maximum length and the
			// maximum rating

			Map.Entry<String, Integer> max = themeTotalsList.get(0);
			for (int j = 0; j < themeTotalsList.size(); j++) {
				String tempKey = themeTotalsList.get(j).getKey();
				int totalCount = themeTotalsList.get(j).getValue();
				if (themeMaxRating.get(tempKey) > themeMaxRating
						.get(max.getKey())
						&& (max.getValue() - totalCount) < 10) {
					max = themeTotalsList.get(j);
				}
			}
			String mapKey = max.getKey();
			int mapKeyCount = max.getValue();
			context.write(key, new Text(mapKey + "\t" + mapKeyCount + "\t"
					+ themeMaxRating.get(mapKey)));

		} else {
			context.write(key, new Text("Ignore\t-3\t-3"));
		}
	}

}
