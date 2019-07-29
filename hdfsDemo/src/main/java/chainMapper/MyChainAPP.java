package chainMapper; /**
 * Created by Chris on 2019/1/2.
 */


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.ChainMapper;
import org.apache.hadoop.mapred.lib.ChainReducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MyChainAPP {


    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
        configuration.set( "fs.defaultFS","file:///");

        Job job = Job.getInstance(configuration);
        JobConf conf = new JobConf();
        //设置作业属性
        job.setJobName("MyChainAPP");
        job.setJarByClass(MyChainAPP.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/1.txt"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/2.txt"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/3.txt"));


        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out3"));


        //ChainMapper.addMapper(job, MyChainMapper1.class, LongWritable.class, Text.class, Text.class, IntWritable.class ,conf);
        //ChainMapper.addMapper( MyChainMapper2.class, Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);
        //ChainReducer.setReducer(job, MyChainReduce.class,Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);
        //ChainReducer.addMapper(job, MyReduceMapper.class,Text.class, IntWritable.class, Text.class, IntWritable.class, configuration);

        job.setNumReduceTasks(4);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }

}
