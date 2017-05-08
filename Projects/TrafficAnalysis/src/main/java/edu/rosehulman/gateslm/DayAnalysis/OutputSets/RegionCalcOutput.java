package edu.rosehulman.gateslm.DayAnalysis.OutputSets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class RegionCalcOutput implements WritableComparable<RegionCalcOutput> {

	// Number of data points for the day
	private IntWritable dataCount;
	// Number of heavy flow data points
	private IntWritable heavyFlowCount;
	// Number of medium-heavy flow data points
	private IntWritable mediumHeavyFlowCount;
	// Number of medium flow data points
	private IntWritable mediumFlowCount;
	// Number of light flow data points
	private IntWritable lightFlowCount;
	// Number of free flow data points
	private IntWritable freeFlowCount;
	// Start for heavy(medium-heavy) time
	private IntWritable startHeavyTime;
	// End for heavy(medium-heavy) time
	private IntWritable endHeavyTime;
	// Average speed for heavy time
	private FloatWritable avgSpeed;

	public RegionCalcOutput() {
		set(new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new FloatWritable());
	}

	public RegionCalcOutput(int dc, int hfc, int mhfc, int mfc, int lfc,
			int ffc, int sht, int eht, float avgS) {
		set(new IntWritable(dc), new IntWritable(hfc), new IntWritable(mhfc),
				new IntWritable(mfc), new IntWritable(lfc),
				new IntWritable(ffc), new IntWritable(sht),
				new IntWritable(eht), new FloatWritable(avgS));
	}

	public RegionCalcOutput(IntWritable dc, IntWritable hfc, IntWritable mhfc,
			IntWritable mfc, IntWritable lfc, IntWritable ffc, IntWritable sht,
			IntWritable eht, FloatWritable avgS) {
		set(dc, hfc, mhfc, mfc, lfc, ffc, sht, eht, avgS);
	}

	public void set(IntWritable dc, IntWritable hfc, IntWritable mhfc,
			IntWritable mfc, IntWritable lfc, IntWritable ffc, IntWritable sht,
			IntWritable eht, FloatWritable avgS) {
		this.dataCount = dc;
		this.heavyFlowCount = hfc;
		this.mediumHeavyFlowCount = mhfc;
		this.mediumFlowCount = mfc;
		this.lightFlowCount = lfc;
		this.freeFlowCount = ffc;
		this.startHeavyTime = sht;
		this.endHeavyTime = eht;
		this.avgSpeed = avgS;
	}

	public IntWritable getDataCount() {
		return dataCount;
	}

	public IntWritable getHeavyFlowCount() {
		return heavyFlowCount;
	}

	public IntWritable getMediumHeavyFlowCount() {
		return mediumHeavyFlowCount;
	}

	public IntWritable getMediumFlowCount() {
		return mediumFlowCount;
	}

	public IntWritable getLightFlowCount() {
		return lightFlowCount;
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

	@Override
	public void readFields(DataInput in) throws IOException {
		this.dataCount.readFields(in);
		this.heavyFlowCount.readFields(in);
		this.mediumHeavyFlowCount.readFields(in);
		this.mediumFlowCount.readFields(in);
		this.lightFlowCount.readFields(in);
		this.freeFlowCount.readFields(in);
		this.startHeavyTime.readFields(in);
		this.endHeavyTime.readFields(in);
		this.avgSpeed.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.dataCount.write(out);
		this.heavyFlowCount.write(out);
		this.mediumHeavyFlowCount.write(out);
		this.mediumFlowCount.write(out);
		this.lightFlowCount.write(out);
		this.freeFlowCount.write(out);
		this.startHeavyTime.write(out);
		this.endHeavyTime.write(out);
		this.avgSpeed.write(out);
	}

	@Override
	public int compareTo(RegionCalcOutput rco) {
		int cmp = this.dataCount.compareTo(rco.dataCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.heavyFlowCount.compareTo(rco.heavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumHeavyFlowCount.compareTo(rco.mediumHeavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumFlowCount.compareTo(rco.mediumFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.lightFlowCount.compareTo(rco.lightFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.freeFlowCount.compareTo(rco.freeFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.startHeavyTime.compareTo(rco.startHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.endHeavyTime.compareTo(rco.endHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		return this.avgSpeed.compareTo(rco.avgSpeed);
	}

	@Override
	public String toString() {
		return this.dataCount + "\t" + this.heavyFlowCount + "\t"
				+ this.mediumHeavyFlowCount + "\t" + this.mediumFlowCount + "\t"
				+ this.lightFlowCount + "\t" + this.freeFlowCount + "\t"
				+ this.startHeavyTime + "\t" + this.endHeavyTime + "\t"
				+ this.avgSpeed;
	}

}
