package hdfs.mr.mysql2;


/**
 * Created by Chris on 2019/1/2.
 */


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class WCAPP {


    public static void main(String [] args) throws IOException, ClassNotFoundException, InterruptedException{

        Configuration configuration  = new Configuration();
        //configuration.set( "fs.defaultFS","file:///");

        Job job = Job.getInstance(configuration);
        //设置作业属性
        job.setJobName("WCAPP");
        job.setJarByClass(WCAPP.class);

        //配置数据库信息
        String driverclass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.216.1:3306/big4";
        String username = "root";
        String password = "123456";
        //设置数据库配置
        DBConfiguration.configureDB(job.getConfiguration(),driverclass,url,username,password);

        //设置数据输入内容
        DBInputFormat.setInput(job,MyDBWritable.class,"select id,name,txt from words","select count(*) from words");
        //数据写到数据库里
        DBOutputFormat.setOutput(job,"stats","word","c");


        //FileOutputFormat.setOutputPath(job,new Path("d:/mr/sql/out"));


        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);

        job.setNumReduceTasks(3);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }

}
