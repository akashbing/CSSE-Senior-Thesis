package edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.rosehulman.gateslm.FlowAnalysisGrouping.Sets.RegionFlowAnalysisGroupingSet;

public class RegionFlowAnalysisGroupingMapperAll extends
		Mapper<LongWritable, Text, Text, RegionFlowAnalysisGroupingSet> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] contents = line.split("\t");
		// System.out.println("Full text: " + contents[0]);
		// System.out.println("Split 0: " + contents[0].split("\\*\\*\\*")[0]);
		// System.out.println("Split 1: " + contents[0].split("\\*\\*\\*")[1]);
		String finalKey = contents[0].split("\\*\\*\\*")[0].trim();
		IntWritable dc = new IntWritable(Integer.parseInt(contents[1].trim()));
		IntWritable hfc = new IntWritable(Integer.parseInt(contents[2].trim()));
		IntWritable mhfc = new IntWritable(
				Integer.parseInt(contents[3].trim()));
		IntWritable mfc = new IntWritable(Integer.parseInt(contents[4].trim()));
		IntWritable lfc = new IntWritable(Integer.parseInt(contents[5].trim()));
		IntWritable ffc = new IntWritable(Integer.parseInt(contents[6].trim()));
		IntWritable sht = new IntWritable(Integer.parseInt(contents[7].trim()));
		IntWritable eht = new IntWritable(Integer.parseInt(contents[8].trim()));
		FloatWritable avgS = new FloatWritable(
				Float.parseFloat(contents[9].trim()));
		Text th = new Text(contents[10].trim());
		IntWritable r = new IntWritable(Integer.parseInt(contents[11].trim()));

		context.write(new Text(finalKey),
				new RegionFlowAnalysisGroupingSet(
						new Text(contents[0].split("\\*\\*\\*")[1].trim()), dc,
						hfc, mhfc, mfc, lfc, ffc, sht, eht, avgS, th, r));

	}

}
