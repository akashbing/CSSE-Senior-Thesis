package edu.rosehulman.gateslm.DayAnalysis;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.DayAnalysis.OutputSets.SegmentCalcOutput;
import edu.rosehulman.gateslm.DayAnalysis.SetMapper.SegmentSetMapper;
import edu.rosehulman.gateslm.DayAnalysis.SetReducer.SegmentSetReducer;
import edu.rosehulman.gateslm.DayAnalysis.Sets.SegmentSet;

public class SegmentRunner {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 2) {
			System.err
					.println("Usage: SegmentRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(SegmentRunner.class);
		job.setJobName("SegmentRunner");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(SegmentSet.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(SegmentCalcOutput.class);

		job.setMapperClass(SegmentSetMapper.class);
		job.setReducerClass(SegmentSetReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
