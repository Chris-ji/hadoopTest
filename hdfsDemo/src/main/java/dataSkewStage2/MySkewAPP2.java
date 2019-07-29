package dataSkewStage2; /**
 * Created by Chris on 2019/1/2.
 */


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MySkewAPP2 {


    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
       configuration.set( "fs.defaultFS","file:///");
        //删除目录
      /* if(args.length>1){
           FileSystem.get(configuration).delete( new Path( args[1]));

       }*/
        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("MySkewAPP2");
        job.setJarByClass(MySkewAPP2.class);
        // job.setInputFormatClass(TextInputFormat.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/out/part-r-00000"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/out/part-r-00001"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/out/part-r-00002"));
        FileInputFormat.addInputPath(job, new Path("d:/mr/skew/out/part-r-00003"));

        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out2"));

       // job.setPartitionerClass(RandomPartitioner.class);//设置分区函数
        job.setMapperClass(MySkewMapper2.class);
        job.setReducerClass(MySkewReduce2.class);

        job.setNumReduceTasks(4);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }

}
