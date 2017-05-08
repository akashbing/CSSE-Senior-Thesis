package edu.rosehulman.gateslm.FlowAnalysisGrouping;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.SegmentFlowAnalysisGroupingMapperAll;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.SegmentFlowAnalysisGroupingMapperMonth;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.SegmentFlowAnalysisGroupingMapperWeek;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetMapper.SegmentFlowAnalysisGroupingMapperYear;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.SetReducer.SegmentFlowAnalysisGroupReducer;
import edu.rosehulman.gateslm.FlowAnalysisGrouping.Sets.SegmentFlowAnalysisGroupingSet;

public class SegmentFlowAnalysisGrouping {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 3) {
			System.err.println(
					"Usage: SegmentRunner <analysis grouping>  <input path> "
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
		job.setJarByClass(SegmentFlowAnalysisGrouping.class);
		job.setJobName("SegmentFlowAnalysisGrouping" + arg3.toUpperCase());

		FileInputFormat.setInputDirRecursive(job, true);
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(SegmentFlowAnalysisGroupingSet.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		if (arg3.equals("week")) {
			job.setMapperClass(SegmentFlowAnalysisGroupingMapperWeek.class);
			job.setReducerClass(SegmentFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("month")) {
			job.setMapperClass(SegmentFlowAnalysisGroupingMapperMonth.class);
			job.setReducerClass(SegmentFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("year")) {
			job.setMapperClass(SegmentFlowAnalysisGroupingMapperYear.class);
			job.setReducerClass(SegmentFlowAnalysisGroupReducer.class);
		} else if (arg3.equals("all")) {
			job.setMapperClass(SegmentFlowAnalysisGroupingMapperAll.class);
			job.setReducerClass(SegmentFlowAnalysisGroupReducer.class);
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
