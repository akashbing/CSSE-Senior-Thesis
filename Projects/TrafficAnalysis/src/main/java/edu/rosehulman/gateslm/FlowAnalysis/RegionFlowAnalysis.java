package edu.rosehulman.gateslm.FlowAnalysis;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.FlowAnalysis.SetMapper.RegionSetFlowAnalysisMapper;
import edu.rosehulman.gateslm.FlowAnalysis.SetReducer.RegionSetFlowAnalysisReducer;
import edu.rosehulman.gateslm.FlowAnalysis.Sets.RegionSetFlowAnalysis;

public class RegionFlowAnalysis {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 2) {
			System.err
					.println("Usage: SegmentRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(RegionFlowAnalysis.class);
		job.setJobName("RegionFlowAnalysis");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(RegionSetFlowAnalysis.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(RegionSetFlowAnalysisMapper.class);
		job.setReducerClass(RegionSetFlowAnalysisReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
