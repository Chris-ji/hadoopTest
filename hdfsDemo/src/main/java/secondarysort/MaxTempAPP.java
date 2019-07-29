package secondarysort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by Chris on 2019/1/5.
 */
public class MaxTempAPP {
    public static void main(String [] args) throws Exception{

        Configuration configuration  = new Configuration();
        configuration.set( "fs.defaultFS","file:///");

        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("SecondarySortAPP");
        job.setJarByClass(MaxTempAPP.class);
        job.setInputFormatClass(TextInputFormat.class);//文本输入格式
        //job.setInputFormatClass(SequenceFileInputFormat.class);//设置输入格式

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReduce.class);

        job.setMapOutputKeyClass(ComboKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        //设置分区类
        job.setPartitionerClass(YearParttioner.class);
        //设置分组类
        job.setGroupingComparatorClass(YearGroupComparator.class);
        //设置排序对比器
        job.setSortComparatorClass(CombokeyComparator.class);

        //设置任务个数
        job.setNumReduceTasks(3);


        job.waitForCompletion(true);
}
}
