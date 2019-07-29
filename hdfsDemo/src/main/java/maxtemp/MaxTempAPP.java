package maxtemp;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;



import org.apache.hadoop.mapred.lib.InputSampler;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
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
        job.setJobName("MaxTempAPP");
        job.setJarByClass(MaxTempAPP.class);
        job.setInputFormatClass(SequenceFileInputFormat.class);//设置输入格式

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReduce.class);

        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
      //设置全排序分区类
        job.setPartitionerClass(TotalOrderPartitioner.class);
        //创建随机采样对象
        //freq :没个key选中概率， numsamplers：抽取样本总数； maxSplitssampler：最大采样切片数
        InputSampler.Sampler<IntWritable, IntWritable> sampler =
                new InputSampler.RandomSampler<IntWritable, IntWritable>(0.1,10000,10);

        //写入分区文件
        InputSampler.writePartitionFile(job,sampler);
        TotalOrderPartitioner.setPartitionFile(configuration , new Path("file:///d:/mr/par.lst"));//取作业的配置



        job.waitForCompletion(true);
}
}
