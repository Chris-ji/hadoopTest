package reduceJoin;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/13.
 */
public class ReduceJoinApp {
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
        configuration.set( "fs.defaultFS","file:///");

        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("ReduceJoinApp");
        job.setJarByClass(ReduceJoinApp.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("D:/mr/join/customers.txt"));
        FileInputFormat.addInputPath(job, new Path("D:/mr/join/orders.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:/mr/join/out"));


        job.setMapperClass(MapJoin.class);
        job.setReducerClass(ReduceJoin.class);

        job.setMapOutputKeyClass(ComboKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setPartitionerClass(CidPartitioner.class);
        job.setGroupingComparatorClass(CIDGroupComparator.class);
        job.setSortComparatorClass(CombokeyComparator.class);

        job.setNumReduceTasks(2);


        job.waitForCompletion(true);

    }
}
