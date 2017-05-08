package edu.rosehulman.gateslm.FlowAnalysis.Sets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class SegmentSetFlowAnalysis
		implements WritableComparable<SegmentSetFlowAnalysis> {

	private Text segmentDate;
	private IntWritable dataCount;
	private IntWritable heavyFlowCount;
	private IntWritable mediumFlowCount;
	private IntWritable freeFlowCount;
	private IntWritable startHeavyTime;
	private IntWritable endHeavyTime;
	private FloatWritable avgSpeed;
	private IntWritable negativeSpeed;
	private Text theme;
	private IntWritable rating;

	public SegmentSetFlowAnalysis(Text sd, IntWritable dc, IntWritable hfc,
			IntWritable mfc, IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns, Text th, IntWritable r) {
		set(sd, dc, hfc, mfc, ffc, sht, eht, avgS, ns, th, r);
	}

	public SegmentSetFlowAnalysis(String sd, int dc, int hfc, int mfc, int ffc,
			int sht, int eht, float avgS, int ns, String th, int r) {
		set(new Text(sd), new IntWritable(dc), new IntWritable(hfc),
				new IntWritable(mfc), new IntWritable(ffc),
				new IntWritable(sht), new IntWritable(eht),
				new FloatWritable(avgS), new IntWritable(ns), new Text(th),
				new IntWritable(r));
	}

	public SegmentSetFlowAnalysis() {
		set(new Text(), new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new IntWritable(),
				new FloatWritable(), new IntWritable(), new Text(),
				new IntWritable());
	}

	public void set(Text sd, IntWritable dc, IntWritable hfc, IntWritable mfc,
			IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns, Text th, IntWritable r) {
		this.segmentDate = sd;
		this.dataCount = dc;
		this.heavyFlowCount = hfc;
		this.mediumFlowCount = mfc;
		this.freeFlowCount = ffc;
		this.startHeavyTime = sht;
		this.endHeavyTime = eht;
		this.avgSpeed = avgS;
		this.negativeSpeed = ns;
		this.theme = th;
		this.rating = r;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.segmentDate.readFields(in);
		this.dataCount.readFields(in);
		this.heavyFlowCount.readFields(in);
		this.mediumFlowCount.readFields(in);
		this.freeFlowCount.readFields(in);
		this.startHeavyTime.readFields(in);
		this.endHeavyTime.readFields(in);
		this.avgSpeed.readFields(in);
		this.negativeSpeed.readFields(in);
		this.theme.readFields(in);
		this.rating.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.segmentDate.write(out);
		this.dataCount.write(out);
		this.heavyFlowCount.write(out);
		this.mediumFlowCount.write(out);
		this.freeFlowCount.write(out);
		this.startHeavyTime.write(out);
		this.endHeavyTime.write(out);
		this.avgSpeed.write(out);
		this.negativeSpeed.write(out);
		this.theme.write(out);
		this.rating.write(out);
	}

	@Override
	public int compareTo(SegmentSetFlowAnalysis ssfa) {
		int cmp = this.segmentDate.compareTo(ssfa.segmentDate);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.dataCount.compareTo(ssfa.dataCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.heavyFlowCount.compareTo(ssfa.heavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumFlowCount.compareTo(ssfa.mediumFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.freeFlowCount.compareTo(ssfa.freeFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.startHeavyTime.compareTo(ssfa.startHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.endHeavyTime.compareTo(ssfa.endHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.avgSpeed.compareTo(ssfa.avgSpeed);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.negativeSpeed.compareTo(ssfa.negativeSpeed);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.theme.compareTo(ssfa.theme);
		if (cmp != 0) {
			return cmp;
		}
		return this.rating.compareTo(rating);
	}

	public Text getSegmentDate() {
		return segmentDate;
	}

	public IntWritable getDataCount() {
		return dataCount;
	}

	public IntWritable getHeavyFlowCount() {
		return heavyFlowCount;
	}

	public IntWritable getMediumFlowCount() {
		return mediumFlowCount;
	}

	public IntWritable getFreeFlowCount() {
		return freeFlowCount;
	}

	public IntWritable getStartHeavyTime() {
		return startHeavyTime;
	}

	public IntWritable getEndHeavyTime() {
		return endHeavyTime;
	}

	public FloatWritable getAvgSpeed() {
		return avgSpeed;
	}

	public IntWritable getNegativeSpeed() {
		return negativeSpeed;
	}

	public Text getTheme() {
		return theme;
	}

	public IntWritable getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return segmentDate + "\t" + dataCount + "\t" + heavyFlowCount + "\t"
				+ mediumFlowCount + "\t" + freeFlowCount + "\t" + startHeavyTime
				+ "\t" + endHeavyTime + "\t" + avgSpeed + "\t" + negativeSpeed
				+ "\t" + theme + "\t" + rating;
	}

}
