package edu.rosehulman.gateslm.CongestionFinder;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.CongestionFinder.Set.RegionTrafficSet;
import edu.rosehulman.gateslm.CongestionFinder.SetMappers.RegionTrafficMapper;
import edu.rosehulman.gateslm.CongestionFinder.SetReducer.RegionTrafficReducer;

public class RegionTrafficRunner {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println(
					"Usage: RegionTrafficRunner <input path> <output path>");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(RegionTrafficRunner.class);
		job.setJobName("RegionTrafficRunner");

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(RegionTrafficSet.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(RegionTrafficMapper.class);
		job.setReducerClass(RegionTrafficReducer.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
