package edu.rosehulman.gateslm.DayAnalysis.Sets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

public class SegmentSet implements WritableComparable<SegmentSet> {

	// private Text segmentId;
	private FloatWritable segmentSpeed;
	private LongWritable segmentTime;

	public SegmentSet() {
		// set(new Text(), new FloatWritable(), new LongWritable());
		set(new FloatWritable(), new LongWritable());
	}

	public SegmentSet(/* String si, */float ss, long st) {
		// set(new Text(si), new FloatWritable(ss), new LongWritable(st));
		set(new FloatWritable(ss), new LongWritable(st));
	}

	public SegmentSet(/* Text si, */ FloatWritable ss, LongWritable st) {
		// set(si, ss, st);
		set(ss, st);
	}

	private void set(/* Text si, */ FloatWritable ss, LongWritable st) {
		// this.segmentId = si;
		this.segmentSpeed = ss;
		this.segmentTime = st;
	}

	// public Text getSegmentId() {
	// return this.segmentId;
	// }

	public FloatWritable getSegmentSpeed() {
		return this.segmentSpeed;
	}

	public LongWritable getSegmentTime() {
		return this.segmentTime;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// this.segmentId.readFields(in);
		this.segmentSpeed.readFields(in);
		this.segmentTime.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// this.segmentId.write(out);
		this.segmentSpeed.write(out);
		this.segmentTime.write(out);
	}

	@Override
	public int compareTo(SegmentSet ss) {
		// Comparing names
		// int cmp = this.segmentId.compareTo(ss.segmentId);
		// if (cmp != 0) {
		// return cmp;
		// }

		// Comparing Speeds
		int cmp = this.segmentSpeed.compareTo(ss.segmentSpeed);
		if (cmp != 0) {
			return cmp;
		}

		// Comparing dates
		return this.segmentTime.compareTo(ss.segmentTime);
	}

	@Override
	public String toString() {
		// return this.segmentId + "\t" + this.segmentSpeed + "\t"
		// + this.segmentTime;
		return this.segmentSpeed + "\t" + this.segmentTime;
	}

}
