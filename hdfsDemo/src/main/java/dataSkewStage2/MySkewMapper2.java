package dataSkewStage2; /**
 * Created by Chris on 2019/1/2.
 */

import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


//前面是输入的kv，后面是输出的kv

/**
 * map函数
 * @author Chris
 *
 */
/*public class MySkewMapper2 extends Mapper<LongWritable, Text, Text, IntWritable>{

    protected void map(LongWritable key,Text value, Context context) throws IOException,InterruptedException {

        String [] arr = value.toString().split( "/t");

      context.write(new Text(arr[0]),new IntWritable(Integer.parseInt(arr[1])));

    }

}*/
public class MySkewMapper2 extends Mapper<Text,Text, Text, IntWritable>{

    protected void map(Text key,Text value, Context context) throws IOException,InterruptedException {
        context.write(key,new IntWritable(Integer.parseInt(value.toString())));

    }

}