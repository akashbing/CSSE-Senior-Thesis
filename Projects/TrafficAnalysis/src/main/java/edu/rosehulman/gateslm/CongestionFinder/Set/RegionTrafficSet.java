package edu.rosehulman.gateslm.CongestionFinder.Set;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class RegionTrafficSet implements WritableComparable<RegionTrafficSet> {

	private Text date;
	private IntWritable dataCount;
	private IntWritable heavyFlowCount;
	private IntWritable mediumHeavyFlowCount;
	private IntWritable mediumFlowCount;
	private IntWritable lightFlowCount;
	private IntWritable freeFlowCount;
	private IntWritable startHeavyTime;
	private IntWritable endHeavyTime;
	private FloatWritable avgSpeed;

	public RegionTrafficSet() {
		set(new Text(), new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new IntWritable(),
				new IntWritable(), new IntWritable(), new FloatWritable());
	}

	public RegionTrafficSet(String d, int dc, int hfc, int mhfc, int mfc,
			int lfc, int ffc, int sht, int eht, float avgS) {
		set(new Text(d), new IntWritable(dc), new IntWritable(hfc),
				new IntWritable(mhfc), new IntWritable(mfc),
				new IntWritable(lfc), new IntWritable(ffc),
				new IntWritable(sht), new IntWritable(eht),
				new FloatWritable(avgS));
	}

	public RegionTrafficSet(Text d, IntWritable dc, IntWritable hfc,
			IntWritable mhfc, IntWritable mfc, IntWritable lfc, IntWritable ffc,
			IntWritable sht, IntWritable eht, FloatWritable avgS) {
		set(d, dc, hfc, mhfc, mfc, lfc, ffc, sht, eht, avgS);
	}

	public void set(Text d, IntWritable dc, IntWritable hfc, IntWritable mhfc,
			IntWritable mfc, IntWritable lfc, IntWritable ffc, IntWritable sht,
			IntWritable eht, FloatWritable avgS) {
		this.date = d;
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

	@Override
	public void readFields(DataInput in) throws IOException {
		this.date.readFields(in);
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
		this.date.write(out);
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
	public int compareTo(RegionTrafficSet rts) {
		int cmp = this.date.compareTo(rts.date);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.dataCount.compareTo(rts.dataCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.heavyFlowCount.compareTo(rts.heavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumHeavyFlowCount.compareTo(rts.mediumHeavyFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.mediumFlowCount.compareTo(rts.mediumFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.lightFlowCount.compareTo(rts.lightFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.freeFlowCount.compareTo(rts.freeFlowCount);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.startHeavyTime.compareTo(rts.startHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		cmp = this.endHeavyTime.compareTo(rts.endHeavyTime);
		if (cmp != 0) {
			return cmp;
		}
		return this.avgSpeed.compareTo(rts.avgSpeed);
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
	public String toString() {
		return date + "\t" + dataCount + "\t" + heavyFlowCount + "\t"
				+ mediumHeavyFlowCount + "\t" + mediumFlowCount + "\t"
				+ lightFlowCount + "\t" + freeFlowCount + "\t" + startHeavyTime
				+ "\t" + endHeavyTime + "\t" + avgSpeed;
	}

}
