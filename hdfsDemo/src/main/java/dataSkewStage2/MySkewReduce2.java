package dataSkewStage2; /**
 * Created by Chris on 2019/1/2.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * reduce函数

 *
 */
public class MySkewReduce2 extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable intWritable : values) {
            count = count  + intWritable.get();
        }

        context.write(key, new IntWritable(count));
    }





}
