package edu.rosehulman.gateslm.CongestionFinder.Set;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class SegmentTrafficSet
		implements WritableComparable<SegmentTrafficSet> {

	private Text date;
	private IntWritable dataCount;
	private IntWritable heavyFlowCount;
	private IntWritable mediumFlowCount;
	private IntWritable freeFlowCount;
	private IntWritable startHeavyTime;
	private IntWritable endHeavyTime;
	private FloatWritable avgSpeed;
	private IntWritable negativeSpeed;

	public SegmentTrafficSet() {
		set(new Text(), new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new IntWritable(),
				new FloatWritable(), new IntWritable());
	}

	public SegmentTrafficSet(Text d, IntWritable dc, IntWritable hfc,
			IntWritable mfc, IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns) {
		set(d, dc, hfc, mfc, ffc, sht, eht, avgS, ns);
	}

	public SegmentTrafficSet(String d, int dc, int hfc, int mfc, int ffc,
			int sht, int eht, float avgS, int ns) {
		set(new Text(d), new IntWritable(dc), new IntWritable(hfc),
				new IntWritable(mfc), new IntWritable(ffc),
				new IntWritable(sht), new IntWritable(eht),
				new FloatWritable(avgS), new IntWritable(ns));
	}

	public void set(Text d, IntWritable dc, IntWritable hfc, IntWritable mfc,
			IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns) {
		this.date = d;
		this.dataCount = dc;
		this.heavyFlowCount = hfc;
		this.mediumFlowCount = mfc;
		this.freeFlowCount = ffc;
		this.startHeavyTime = sht;
		this.endHeavyTime = eht;
		this.avgSpeed = avgS;
		this.negativeSpeed = ns;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.date.readFields(in);
		this.dataCount.readFields(in);
		this.heavyFlowCount.readFields(in);
		this.mediumFlowCount.readFields(in);
		this.freeFlowCount.readFields(in);
		this.startHeavyTime.readFields(in);
		this.endHeavyTime.readFields(in);
		this.avgSpeed.readFields(in);
		this.negativeSpeed.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.date.write(out);
		this.dataCount.write(out);
		this.heavyFlowCount.write(out);
		this.mediumFlowCount.write(out);
		this.freeFlowCount.write(out);
		this.startHeavyTime.write(out);
		this.endHeavyTime.write(out);
		this.avgSpeed.write(out);
		this.negativeSpeed.write(out);
	}

	@Override
	public int compareTo(SegmentTrafficSet sts) {
		int cmp = this.date.compareTo(sts.date);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.dataCount.compareTo(sts.dataCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.heavyFlowCount.compareTo(sts.heavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumFlowCount.compareTo(sts.mediumFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.freeFlowCount.compareTo(sts.freeFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.startHeavyTime.compareTo(sts.startHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.endHeavyTime.compareTo(sts.endHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.avgSpeed.compareTo(sts.avgSpeed);
		if (cmp != 0) {
			return cmp;
		}
		return this.negativeSpeed.compareTo(sts.negativeSpeed);
	}

	@Override
	public String toString() {
		return date + "\t" + dataCount + "\t" + heavyFlowCount + "\t"
				+ mediumFlowCount + "\t" + freeFlowCount + "\t" + startHeavyTime
				+ "\t" + endHeavyTime + "\t" + avgSpeed + "\t" + negativeSpeed;
	}

	public Text getDate() {
		return date;
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

}
