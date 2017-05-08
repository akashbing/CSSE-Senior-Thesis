package edu.rosehulman.gateslm.DayAnalysis.SetMapper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.rosehulman.gateslm.DayAnalysis.Sets.SegmentSet;

public class SegmentSetMapper
		extends Mapper<LongWritable, Text, Text, SegmentSet> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] contents = line.split(",");
		String finalKey = contents[0].trim() + "*****" + contents[4].trim();

		// You only need the Speed and time in long value. The rest was not
		// needed for the time comparison

		context.write(new Text(finalKey),
				new SegmentSet(
						new FloatWritable(Float.parseFloat(contents[1].trim())),
						new LongWritable(Long.parseLong(contents[2].trim()))));
	}

}
