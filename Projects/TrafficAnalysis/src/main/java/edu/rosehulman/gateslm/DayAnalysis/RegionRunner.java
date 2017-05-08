package edu.rosehulman.gateslm.DayAnalysis;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.DayAnalysis.OutputSets.RegionCalcOutput;
import edu.rosehulman.gateslm.DayAnalysis.SetMapper.RegionSetMapper;
import edu.rosehulman.gateslm.DayAnalysis.SetReducer.RegionSetReducer;
import edu.rosehulman.gateslm.DayAnalysis.Sets.RegionSet;

public class RegionRunner {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: RegionRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(RegionRunner.class);
		job.setJobName("RegionRunner");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(RegionSet.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(RegionCalcOutput.class);

		job.setMapperClass(RegionSetMapper.class);
		job.setReducerClass(RegionSetReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
