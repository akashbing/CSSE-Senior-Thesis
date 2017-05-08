package edu.rosehulman.gateslm.FlowAnalysis;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.FlowAnalysis.SetMapper.SegmentSetFlowAnalysisMapper;
import edu.rosehulman.gateslm.FlowAnalysis.SetReducer.SegmentSetFlowAnalysisReducer;
import edu.rosehulman.gateslm.FlowAnalysis.Sets.SegmentSetFlowAnalysis;

public class SegmentFlowAnalysis {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 2) {
			System.err
					.println("Usage: SegmentRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(SegmentFlowAnalysis.class);
		job.setJobName("SegmentFlowAnalysis");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(SegmentSetFlowAnalysis.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(SegmentSetFlowAnalysisMapper.class);
		job.setReducerClass(SegmentSetFlowAnalysisReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
