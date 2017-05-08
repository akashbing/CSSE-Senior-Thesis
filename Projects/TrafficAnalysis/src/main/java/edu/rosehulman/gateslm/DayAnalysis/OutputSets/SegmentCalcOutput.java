package edu.rosehulman.gateslm.DayAnalysis.OutputSets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class SegmentCalcOutput
		implements WritableComparable<SegmentCalcOutput> {

	private IntWritable dataCount;

	private IntWritable heavyFlowCount;

	private IntWritable mediumFlowCount;

	private IntWritable freeFlowCount;

	private IntWritable startHeavyTime;

	private IntWritable endHeavyTime;

	private FloatWritable avgSpeed;

	private IntWritable negativeSpeed;

	public SegmentCalcOutput() {
		set(new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new IntWritable(),
				new FloatWritable(), new IntWritable());
	}

	public SegmentCalcOutput(IntWritable dc, IntWritable hfc, IntWritable mfc,
			IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns) {
		set(dc, hfc, mfc, ffc, sht, eht, avgS, ns);
	}

	public SegmentCalcOutput(int dc, int hfc, int mfc, int ffc, int sht,
			int eht, float avgS, int ns) {
		set(new IntWritable(dc), new IntWritable(hfc), new IntWritable(mfc),
				new IntWritable(ffc), new IntWritable(sht),
				new IntWritable(eht), new FloatWritable(avgS),
				new IntWritable(ns));
	}

	public void set(IntWritable dc, IntWritable hfc, IntWritable mfc,
			IntWritable ffc, IntWritable sht, IntWritable eht,
			FloatWritable avgS, IntWritable ns) {
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
	public int compareTo(SegmentCalcOutput sco) {
		int cmp = this.dataCount.compareTo(sco.dataCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.heavyFlowCount.compareTo(sco.heavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumFlowCount.compareTo(sco.mediumFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.freeFlowCount.compareTo(sco.freeFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.startHeavyTime.compareTo(sco.startHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.endHeavyTime.compareTo(sco.endHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.avgSpeed.compareTo(sco.avgSpeed);
		if (cmp != 0) {
			return cmp;
		}
		return this.negativeSpeed.compareTo(sco.negativeSpeed);
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

	public IntWritable getFreeFlowCondition() {
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

	@Override
	public String toString() {
		return dataCount + "\t" + heavyFlowCount + "\t" + mediumFlowCount + "\t"
				+ freeFlowCount + "\t" + startHeavyTime + "\t" + endHeavyTime
				+ "\t" + avgSpeed + "\t" + negativeSpeed;
	}

}
