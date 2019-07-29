package data.skew; /**
 * Created by Chris on 2019/1/2.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MySkewAPP {


    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
       configuration.set( "fs.defaultFS","file:///");
        //删除目录
      /* if(args.length>1){
           FileSystem.get(configuration).delete( new Path( args[1]));

       }*/
        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("MySkewAPP");
        job.setJarByClass(MySkewAPP.class);
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/1.txt"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/2.txt"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/3.txt"));

        //FileInputFormat.setInputPaths(job,"d:/mr/skew/1.tx","d:/mr/skew/2.txt","d:/mr/skew/3.txt"));
        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out"));

        job.setPartitionerClass(RandomPartitioner.class);//设置分区函数
        job.setMapperClass(MySkewMapper.class);
        job.setReducerClass(MySkewReduce.class);

        job.setNumReduceTasks(4);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }

}
