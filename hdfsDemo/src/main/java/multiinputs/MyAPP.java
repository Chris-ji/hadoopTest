package multiinputs; /**
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


public class MyAPP {
    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
       configuration.set( "fs.defaultFS","file:///");
      /*  //删除目录
        if(args.length>1){
           FileSystem.get(configuration).delete( new Path( args[1]));
        }*/
        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("MyAPP");
        job.setJarByClass(MyAPP.class);


        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);

        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }

}
