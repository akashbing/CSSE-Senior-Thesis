package edu.rosehulman.gateslm.CongestionFinder;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.CongestionFinder.Set.SegmentTrafficSet;
import edu.rosehulman.gateslm.CongestionFinder.SetMappers.SegmentTrafficMapper;
import edu.rosehulman.gateslm.CongestionFinder.SetReducer.SegmentTrafficReducer;

public class SegmentTrafficRunner {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println(
					"Usage: SegmentTrafficRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(SegmentTrafficRunner.class);
		job.setJobName("SegmentTrafficRunner");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(SegmentTrafficSet.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(SegmentTrafficMapper.class);
		job.setReducerClass(SegmentTrafficReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
