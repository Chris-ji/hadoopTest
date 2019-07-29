package multiinputs; /**
 * Created by Chris on 2019/1/2.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


//前面是输入的kv，后面是输出的kv

/**
 * map函数
 * @author Chris
 *
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
    public  MyMapper(){
        System.out.println("new MyMapper()");
    }
    protected void map(LongWritable key,Text value, Context context) throws IOException,InterruptedException {
        Text keyOut = new Text();
        IntWritable valueOut = new IntWritable();
        String [] arr = value.toString().split( " ");
        for (String s : arr) {
            keyOut.set(s);
            valueOut.set(1);
            context.write(keyOut, valueOut);
            context.getCounter("m","MyMapper.map").increment(1);
        }
    }

}
