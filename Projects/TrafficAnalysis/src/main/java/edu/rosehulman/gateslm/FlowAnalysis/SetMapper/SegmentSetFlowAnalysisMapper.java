package edu.rosehulman.gateslm.FlowAnalysis.SetMapper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.rosehulman.gateslm.FlowAnalysis.Sets.SegmentSetFlowAnalysis;

public class SegmentSetFlowAnalysisMapper
		extends Mapper<LongWritable, Text, IntWritable, SegmentSetFlowAnalysis> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] contents = line.split("\t");
		int finalKey = Integer
				.parseInt(contents[0].split("\\*\\*\\*\\*\\*")[0].trim());
		IntWritable dc = new IntWritable(Integer.parseInt(contents[1].trim()));
		IntWritable hfc = new IntWritable(Integer.parseInt(contents[2].trim()));
		IntWritable mfc = new IntWritable(Integer.parseInt(contents[3].trim()));
		IntWritable ffc = new IntWritable(Integer.parseInt(contents[4].trim()));
		IntWritable sht = new IntWritable(Integer.parseInt(contents[5].trim()));
		IntWritable eht = new IntWritable(Integer.parseInt(contents[6].trim()));
		FloatWritable avgS = new FloatWritable(
				Float.parseFloat(contents[7].trim()));
		IntWritable ns = new IntWritable(Integer.parseInt(contents[8].trim()));
		Text theme = new Text(contents[9].trim());
		IntWritable rating = new IntWritable(Integer.parseInt(contents[10].trim()));
		context.write(new IntWritable(finalKey),
				new SegmentSetFlowAnalysis(
						new Text(
								contents[0].split("\\*\\*\\*\\*\\*")[1].trim()),
						dc, hfc, mfc, ffc, sht, eht, avgS, ns, theme, rating));
	}

}
