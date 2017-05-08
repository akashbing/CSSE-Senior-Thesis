package edu.rosehulman.gateslm.DayAnalysis.SetReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import edu.rosehulman.gateslm.DayAnalysis.Helpers.CongestionHolder;
import edu.rosehulman.gateslm.DayAnalysis.Helpers.SpeedTimeHolder;
import edu.rosehulman.gateslm.DayAnalysis.OutputSets.RegionCalcOutput;
import edu.rosehulman.gateslm.DayAnalysis.Sets.RegionSet;

public class RegionSetReducer
		extends Reducer<Text, RegionSet, Text, RegionCalcOutput> {

	private static final int HOUR_IN_SECONDS = 60 * 60;

	@Override
	protected void reduce(Text key, Iterable<RegionSet> values, Context context)
			throws IOException, InterruptedException {
		int dataCount = 0;
		int hfc = 0;
		int mhfc = 0;
		int mfc = 0;
		int lfc = 0;
		int ffc = 0;

		List<SpeedTimeHolder> ls = new ArrayList<>();

		for (RegionSet rs : values) {
			dataCount += 1;
			float speed = rs.getRegionSpeed().get();
			long time = rs.getRegionTime().get();
			if (speed >= 25) {
				ffc++;
			} else if (speed >= 20) {
				lfc++;
			} else if (speed >= 15) {
				mfc++;
			} else if (speed >= 10) {
				ls.add(new SpeedTimeHolder(speed, time));
				mhfc++;
			} else { // speed < 10
				ls.add(new SpeedTimeHolder(speed, time));
				hfc++;
			}
		}

		ls.sort(new Comparator<SpeedTimeHolder>() {
			@Override
			public int compare(SpeedTimeHolder sth1, SpeedTimeHolder sth2) {
				long timeDiff = sth1.getTime() - sth2.getTime();
				if (timeDiff < 0) {
					return -1;
				} else if (timeDiff > 0) {
					return +1;
				}
				return 0;
			}
		});

		List<CongestionHolder> chList = new ArrayList<>();

		CongestionHolder currentCh = null;
		for (int i = 0; i < ls.size() - 1; i++) {
			SpeedTimeHolder current = ls.get(i);
			SpeedTimeHolder next = ls.get(i + 1);
			if (currentCh == null) {
				currentCh = new CongestionHolder(current.timeOfDaySeconds());
				currentCh.addSpeed(current.getSpeed());
			}
			if (next.timeOfDaySeconds()
					- current.timeOfDaySeconds() < HOUR_IN_SECONDS) {
				currentCh.addSpeed(next.getSpeed());
			} else {
				currentCh.setEndTime(current.timeOfDaySeconds());
				chList.add(currentCh);
				currentCh = new CongestionHolder(next.timeOfDaySeconds());
				currentCh.addSpeed(next.getSpeed());
			}
			if (i == ls.size() - 2) {
				currentCh.setEndTime(next.timeOfDaySeconds());
				chList.add(currentCh);
			}
		}

		// System.out.println("**** Number of congestion times: " +
		// chList.size());

		CongestionHolder maxCongestion = null;
		for (CongestionHolder ch : chList) {
			if (maxCongestion == null) {
				maxCongestion = ch;
				continue;
			}
			if (maxCongestion.getTimeCongested() < ch.getTimeCongested()) {
				maxCongestion = ch;
			}
		}

		if (maxCongestion == null) {
			maxCongestion = new CongestionHolder(-1);
			maxCongestion.setEndTime(-1);
		}
		// System.out.println("**** " + key.toString());
		context.write(key, new RegionCalcOutput(dataCount, hfc, mhfc, mfc, lfc,
				ffc, maxCongestion.getStartTime(), maxCongestion.getEndTime(),
				maxCongestion.getAvgSpeed()));
	}

}
