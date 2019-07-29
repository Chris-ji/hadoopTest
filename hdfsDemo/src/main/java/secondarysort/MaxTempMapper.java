package secondarysort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Chris on 2019/1/5.
 */
public class MaxTempMapper extends Mapper <LongWritable,Text,ComboKey,NullWritable>{

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String arr[] = line.split("/t ");
        ComboKey keyOut = new ComboKey();
        keyOut.setYear(Integer.parseInt(arr[0]));
        keyOut.setTemp(Integer.parseInt(arr[1]));
        context.write(keyOut,NullWritable.get());
    }
}
