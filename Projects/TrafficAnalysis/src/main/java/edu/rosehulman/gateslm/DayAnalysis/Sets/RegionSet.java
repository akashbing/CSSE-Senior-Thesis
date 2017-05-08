package edu.rosehulman.gateslm.DayAnalysis.Sets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

public class RegionSet implements WritableComparable<RegionSet> {

	// private Text regionName;
	private FloatWritable regionSpeed;
	private LongWritable regionTime;

	public RegionSet() {
		set(/* new Text(), */ new FloatWritable(), new LongWritable());
	}

	public RegionSet(/* String rn, */float rs, long rt) {
		// set(new Text(rn), new FloatWritable(rs), new LongWritable(rt));
		set(new FloatWritable(rs), new LongWritable(rt));
	}

	public RegionSet(/* Text rn, */ FloatWritable rs, LongWritable rt) {
		// set(rn, rs, rt);
		set(rs, rt);
	}

	private void set(/* Text rn, */ FloatWritable rs, LongWritable rt) {
		// this.regionName = rn;
		this.regionSpeed = rs;
		this.regionTime = rt;
	}

	// public Text getRegionName() {
	// return regionName;
	// }

	public FloatWritable getRegionSpeed() {
		return regionSpeed;
	}

	public LongWritable getRegionTime() {
		return regionTime;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// this.regionName.readFields(in);
		this.regionSpeed.readFields(in);
		this.regionTime.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// this.regionName.write(out);
		this.regionSpeed.write(out);
		this.regionTime.write(out);
	}

	@Override
	public int compareTo(RegionSet rs) {
		// Comparing names
		// int cmp = this.regionName.compareTo(rs.regionName);
		// if (cmp != 0) {
		// return cmp;
		// }

		// Comparing Speeds
		int cmp = this.regionSpeed.compareTo(rs.regionSpeed);
		if (cmp != 0) {
			return cmp;
		}

		// Comparing dates
		return this.regionTime.compareTo(rs.regionTime);
	}

	@Override
	public String toString() {
		// return this.regionName + "\t" + this.regionSpeed + "\t"
		// + this.regionTime;
		return this.regionSpeed + "\t" + this.regionTime;
	}
}
