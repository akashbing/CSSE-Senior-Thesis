package edu.rosehulman.gateslm.DayAnalysis.SetReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import edu.rosehulman.gateslm.DayAnalysis.Helpers.CongestionHolder;
import edu.rosehulman.gateslm.DayAnalysis.Helpers.SpeedTimeHolder;
import edu.rosehulman.gateslm.DayAnalysis.OutputSets.SegmentCalcOutput;
import edu.rosehulman.gateslm.DayAnalysis.Sets.SegmentSet;

public class SegmentSetReducer
		extends Reducer<Text, SegmentSet, Text, SegmentCalcOutput> {

	private static final int HOUR_IN_SECONDS = 60 * 60;

	@Override
	protected void reduce(Text key, Iterable<SegmentSet> values,
			Context context) throws IOException, InterruptedException {

		int dataCount = 0;
		int hfc = 0;
		int mfc = 0;
		int ffc = 0;
		int ns = 0;

		List<SpeedTimeHolder> ls = new ArrayList<>();

		for (SegmentSet ss : values) {
			dataCount += 1;
			float speed = ss.getSegmentSpeed().get();
			long time = ss.getSegmentTime().get();
			if (speed >= 25) { // Changed from 21 to 25 to include more times in
								// high congestion
				ffc++;
			} else if (speed >= 15) { // Changed from 10 to 15 to include more
										// times in the high congestion
				mfc++;
			} else if (speed >= 0) {
				hfc++;
				ls.add(new SpeedTimeHolder(speed, time));
			} else { // speed < 0
				ns++;
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
			SpeedTimeHolder next = ls.get(i);

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

		context.write(key, new SegmentCalcOutput(dataCount, hfc, mfc, ffc,
				maxCongestion.getStartTime(), maxCongestion.getEndTime(),
				maxCongestion.getAvgSpeed(), ns));
	}

}
