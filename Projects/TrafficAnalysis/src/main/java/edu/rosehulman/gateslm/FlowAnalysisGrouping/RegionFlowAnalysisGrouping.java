package edu.rosehulman.gateslm.FlowAnalysisGrouping;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.RegionFlowAnalysisGroupingMapperAll;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.RegionFlowAnalysisGroupingMapperMonth;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.RegionFlowAnalysisGroupingMapperWeek;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.RegionFlowAnalysisGroupingMapperYear;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetReducer.RegionFlowAnalysisGroupReducer;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.Sets.RegionFlowAnalysisGroupingSet;

public class RegionFlowAnalysisGrouping {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 3) {
			System.err.println(
					"Usage: RegionRunner <analysis grouping>  <input path> "
							+ "<output path>");
			System.exit(-1);
		}

		String arg3 = args[0].trim();

		if (!arg3.equals("week") && !arg3.equals("month")
				&& !arg3.equals("year") && !arg3.equals("all")) {
			System.err.println("Analysis Grouping: \n"
					+ "Split up by Weeks (and Year) -> week\n"
					+ "Split up by Months (and Year) -> month\n"
					+ "Split up by Years -> year\n"
					+ "All calculations -> all");
			System.exit(-1);
		}

		Job job = Job.getInstance();
		job.setJarByClass(RegionFlowAnalysisGrouping.class);
		job.setJobName("RegionFlowAnalysisGrouping" + arg3.toUpperCase());

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(RegionFlowAnalysisGroupingSet.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		if (arg3.equals("week")) {
			job.setMapperClass(RegionFlowAnalysisGroupingMapperWeek.class);
			job.setReducerClass(RegionFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("month")) {
			job.setMapperClass(RegionFlowAnalysisGroupingMapperMonth.class);
			job.setReducerClass(RegionFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("year")) {
			job.setMapperClass(RegionFlowAnalysisGroupingMapperYear.class);
			job.setReducerClass(RegionFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("all")) {
			job.setMapperClass(RegionFlowAnalysisGroupingMapperAll.class);
			job.setReducerClass(RegionFlowAnalysisGroupReducer.class);
		} else {
			System.err.println("Analysis Grouping: \n"
					+ "Split up by Weeks (and Year) -> week\n"
					+ "Split up by Months (and Year) -> month\n"
					+ "Split up by Years -> year\n"
					+ "All calculations -> all");
			System.exit(-1);
		}

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
