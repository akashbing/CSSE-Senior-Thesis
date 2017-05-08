package edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper;

import java.io.IOException;
import java.util.Calendar;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.rosehulman.gateslm.FlowAnalysisGrouping.Sets.SegmentFlowAnalysisGroupingSet;

public class SegmentFlowAnalysisGroupingMapperYear extends
		Mapper<LongWritable, Text, Text, SegmentFlowAnalysisGroupingSet> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] contents = line.split("\t");
		String finalKey = contents[0].split("\\*\\*\\*\\*\\*")[0].trim();
		String finalKeyDate = contents[0].split("\\*\\*\\*\\*\\*")[1].trim();
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(finalKeyDate.split("-")[0]),
				Integer.parseInt(finalKeyDate.split("-")[2]) - 1,
				Integer.parseInt(finalKeyDate.split("-")[1]));
		finalKey = finalKey + "\t" + c.get(Calendar.YEAR);
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
		IntWritable rating = new IntWritable(
				Integer.parseInt(contents[10].trim()));
		context.write(new Text(finalKey),
				new SegmentFlowAnalysisGroupingSet(
						new Text(
								contents[0].split("\\*\\*\\*\\*\\*")[1].trim()),
						dc, hfc, mfc, ffc, sht, eht, avgS, ns, theme, rating));

	}

}
