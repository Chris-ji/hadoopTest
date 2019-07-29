package maxtemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/5.
 */
public class MaxTempMapper extends Mapper <IntWritable,IntWritable,IntWritable,IntWritable>{

    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
       // String line = value.toString();
       // String arr[] = line.split(" ");
      // context.write(new IntWritable(Integer.parseInt(arr[0])),new IntWritable(Integer.parseInt(arr[1])));//正常输出
        context.write(key,value);//全排序

    }


}
